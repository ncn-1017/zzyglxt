package com.zyyglxt.service.impl;

import com.zyyglxt.dao.ResourcesDOMapper;
import com.zyyglxt.dao.ResourcesRoleRefDOMapper;
import com.zyyglxt.dao.RoleDOMapper;
import com.zyyglxt.dataobject.*;

import com.zyyglxt.error.BusinessException;
import com.zyyglxt.error.EmBusinessError;

import com.zyyglxt.util.*;

import com.zyyglxt.service.ResourcesService;
import com.zyyglxt.util.DateUtils;
import com.zyyglxt.util.MenuTreeUtil;
import com.zyyglxt.util.UUIDUtils;
import com.zyyglxt.validator.ValidatorImpl;
import com.zyyglxt.validator.ValidatorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wanglx
 * @Date 2020/10/29 0029 11:58
 * @Version 1.0
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    ResourcesDOMapper resourcesDOMapper;
    @Autowired
    RoleDOMapper roleDOMapper;
    @Autowired
    ResourcesRoleRefDOMapper resourcesRoleRefDOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Autowired
    UsernameUtil usernameUtil;

    @Override
    public void deleteByPrimaryKey(ResourcesDO resourcesDO) {
        //
        ResourcesRoleRefDOKey resRoleRefDOKey = new ResourcesRoleRefDOKey();
        ResourcesRoleRefDO resRoleRefDO = resourcesRoleRefDOMapper.selectByResCode(resourcesDO.getItemcode());
        resRoleRefDOKey.setItemid(resRoleRefDO.getItemid());
        resRoleRefDOKey.setItemcode(resRoleRefDO.getItemcode());
        resourcesRoleRefDOMapper.deleteByPrimaryKey(resRoleRefDOKey);
        //
        ResourcesDOKey resourcesDOKey = new ResourcesDOKey();
        resourcesDOKey.setItemid(resourcesDO.getItemid());
        resourcesDOKey.setItemcode(resourcesDO.getItemcode());
        resourcesDOMapper.deleteByPrimaryKey(resourcesDOKey);
    }

    @Override
    public void insertSelective(ResourcesDO record) {
        ValidatorResult result = validator.validate(record);
        if(result.isHasErrors()){
            throw new BusinessException(result.getErrMsg(), EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        record.setItemcode(UUIDUtils.getUUID());
        resourcesDOMapper.insertSelective(record);
        ResourcesRoleRefDO resourcesRoleRefDO = new ResourcesRoleRefDO();
        resourcesRoleRefDO.setItemcode(UUIDUtils.getUUID());
        resourcesRoleRefDO.setResourceCode(record.getItemcode());
        resourcesRoleRefDO.setRoleCode(roleDOMapper.selectByRoleName(record.getRoleNmae()).getItemcode());
        resourcesRoleRefDOMapper.insertSelective(resourcesRoleRefDO);
    }

    @Override
    public ResourcesDO selectByPrimaryKey(ResourcesDOKey key) {
        return resourcesDOMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(ResourcesDO record) {
        record.setUpdater(usernameUtil.getOperateUser());
        record.setItemupdateat(DateUtils.getDate());
        return resourcesDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ResourcesDO> selectAllResources() {
        List<ResourcesDO> resourcesDOS = resourcesDOMapper.selectAllResources();
        MenuTreeUtil menuTreeUtil = new MenuTreeUtil(resourcesDOS, null);
        List<ResourcesDO> treeGridList = menuTreeUtil.buildTreeGrid();
        return treeGridList;
    }

    @Override
    public List<ResourcesDO> SelectMenuByRoleCode(UserDO userDO) {
        RoleDO roleDO = roleDOMapper.selectByUserid(userDO.getItemcode());
        List<ResourcesDO> resourcesDOS = resourcesDOMapper.SelectMenuByRoleCode(roleDO.getItemcode());
        MenuTreeUtil menuTreeUtil = new MenuTreeUtil(resourcesDOS, null);
        return menuTreeUtil.buildTreeGrid();
    }

    @Override
    public List<ResourcesDO> selectListByPath(String requestUrl) {
        return resourcesDOMapper.selectListByPath(requestUrl);
    }

    @Override
    public List<ResourcesDO> SelectPermissionByRoleCode(UserDO userDO) {
        RoleDO roleDO = roleDOMapper.selectByUserid(userDO.getItemcode());
        return resourcesDOMapper.SelectPermissionByRoleCode(roleDO.getItemcode());
    }

    @Override
    public List<ResourcesDO> selectPres() {
        return resourcesDOMapper.selectPres();
    }
}
