package com.yqz.pth.service;

import com.yqz.pth.mbg.model.UmsAdmin;
import com.yqz.pth.mbg.model.UmsPermission;

import java.util.List;

/**
 * @author zzh
 * @Description
 * @date 2020-09-07 20:29
 */
public interface UmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    List<UmsPermission> getPermissionList(Long id);

    UmsAdmin register(UmsAdmin umsAdmin);
}
