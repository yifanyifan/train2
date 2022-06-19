package com.metadata.train.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metadata.train.common.ResultEntity;
import com.metadata.train.common.ResultEntityEnum;
import com.metadata.train.common.StudentRegistryApproveEnum;
import com.metadata.train.entity.common.PageEntity;
import com.metadata.train.entity.sys.StudentRegistry;
import com.metadata.train.entity.sys.User;
import com.metadata.train.sys.mapper.StudentRegistryMapper;
import com.metadata.train.sys.mapper.UserMapper;
import com.metadata.train.sys.param.StudentRegistryPageParam;
import com.metadata.train.sys.service.StudentRegistryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 学员注册表 服务实现类
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Slf4j
@Service
public class StudentRegistryServiceImpl extends ServiceImpl<StudentRegistryMapper, StudentRegistry> implements StudentRegistryService {

    @Autowired
    private StudentRegistryMapper studentRegistryMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultEntity saveStudentRegistry(StudentRegistry studentRegistry) throws Exception {
        ResultEntity restResult = null;
        List<StudentRegistry> studentRegistryExistList = studentRegistryMapper.selectList(new LambdaQueryWrapper<StudentRegistry>().eq(StudentRegistry::getCode, studentRegistry.getCode()));
        if (CollUtil.isNotEmpty(studentRegistryExistList)) {
            //判断提交的最后一个申请的状态
            StudentRegistry studentRegistryExistRecord = studentRegistryExistList.get(studentRegistryExistList.size() - 1);
            if (studentRegistryExistRecord.getApproveStatus().equalsIgnoreCase(StudentRegistryApproveEnum.WAIT_APPROVE.getCode())) {
                restResult = ResultEntity.generate(ResultEntityEnum.DATA_EXISTED, "您已提交注册申请，请等待管理员审核", studentRegistryExistRecord);
            } else if (studentRegistryExistRecord.getApproveStatus().equalsIgnoreCase(StudentRegistryApproveEnum.DISAGREE.getCode())) {
                //提交未通过，提交重复提交申请
                studentRegistry.setApproveStatus(StudentRegistryApproveEnum.WAIT_APPROVE.getCode());
                studentRegistryMapper.insert(studentRegistry);
                restResult = ResultEntity.success(studentRegistry);
            } else if (studentRegistryExistRecord.getApproveStatus().equalsIgnoreCase(StudentRegistryApproveEnum.AGREE.getCode())) {
                restResult = ResultEntity.generate(ResultEntityEnum.DATA_EXISTED, "您提交的注册申请已通过审核，请登录访问系统", studentRegistryExistRecord);
            }
        } else {
            User studentByStuCode = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getCode, studentRegistry.getCode()));
            if (ObjectUtil.isNotEmpty(studentByStuCode)) {
                restResult = ResultEntity.generate(ResultEntityEnum.DATA_EXISTED, "此证件号用户已存在，请直接登录访问系统", null);
            } else {
                studentRegistry.setLoginName(studentRegistry.getCode());
                studentRegistry.setApproveStatus(StudentRegistryApproveEnum.WAIT_APPROVE.getCode());
                studentRegistryMapper.insert(studentRegistry);
                restResult = ResultEntity.success(studentRegistry);
            }
        }
        return restResult;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateStudentRegistry(StudentRegistry studentRegistry) throws Exception {
        return super.updateById(studentRegistry);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteStudentRegistry(Long id) throws Exception {
        return super.removeById(id);
    }


    @Override
    public PageEntity<StudentRegistry> getStudentRegistryPageList(StudentRegistryPageParam studentRegistryPageParam) throws Exception {
        Page<StudentRegistry> page = new Page<>(studentRegistryPageParam.getPageIndex(), studentRegistryPageParam.getPageSize());
        LambdaQueryWrapper<StudentRegistry> wrapper = getLambdaQueryWrapper(studentRegistryPageParam);
        IPage<StudentRegistry> iPage = studentRegistryMapper.selectPage(page, wrapper);
        return new PageEntity<StudentRegistry>(iPage);
    }

    @Override
    public List<StudentRegistry> getStudentRegistryList(StudentRegistryPageParam studentRegistryPageParam) throws Exception {
        LambdaQueryWrapper<StudentRegistry> wrapper = getLambdaQueryWrapper(studentRegistryPageParam);
        List<StudentRegistry> StudentRegistryList = studentRegistryMapper.selectList(wrapper);
        return StudentRegistryList;
    }

    private LambdaQueryWrapper<StudentRegistry> getLambdaQueryWrapper(StudentRegistryPageParam studentRegistryPageParam) {
        LambdaQueryWrapper<StudentRegistry> wrapper = new LambdaQueryWrapper<>();
        return wrapper;
    }

}
