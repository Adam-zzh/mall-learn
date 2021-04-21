package com.yqz.pth.service;

import com.yqz.pth.mapper.UmsAdminRoleRelationDao;
import com.yqz.pth.mbg.model.UmsPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-09-09 01:21
 */
@Service
@Transactional
public class UmsAdminRoleRelationServiceImpl implements UmsAdminRoleRelationService {
    @Autowired
    private UmsAdminRoleRelationDao userRoleDao;
    @Override
    public List<UmsPermission> getAllPermission(Long userId) {
        return userRoleDao.getPermissionList(userId);
    }
}
