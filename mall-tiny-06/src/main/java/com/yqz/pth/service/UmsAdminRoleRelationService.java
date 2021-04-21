package com.yqz.pth.service;

import com.yqz.pth.mbg.model.UmsPermission;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-09-09 01:19
 */
public interface UmsAdminRoleRelationService {
    List<UmsPermission> getAllPermission(Long userId);
}
