package com.metadata.train.auth.service;

import com.metadata.train.auth.AuthConstant;
import com.metadata.train.auth.constant.MessageConstant;
import com.metadata.train.auth.entity.SecurityUser;
import com.metadata.train.auth.feign.UserCilents;
import com.metadata.train.entity.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 添加UserService实现UserDetailsService接口，用于加载用户信息：
 */
@Service
public class UserService implements UserDetailsService {
    /*private List<UserDetail> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new UserDetail("macro", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new UserDetail("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new UserDetail("mark", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }*/

    @Autowired
    private UserCilents userCilent;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");

        User user = null;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            user = userCilent.loadUserByUsername(username);
        } else {
            //userDto = memberService.loadUserByUsername(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        user.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(user);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;

        /*List<UserDetail> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }*/
    }


}
