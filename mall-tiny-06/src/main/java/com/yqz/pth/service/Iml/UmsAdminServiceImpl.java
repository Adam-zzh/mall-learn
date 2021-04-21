//package com.yqz.pth.service.Iml;
//
//import com.yqz.pth.mapper.UmsAdminRoleRelationDao;
//import com.yqz.pth.mbg.mapper.UmsAdminMapper;
//import com.yqz.pth.mbg.model.UmsAdmin;
//import com.yqz.pth.mbg.model.UmsAdminExample;
//import com.yqz.pth.mbg.model.UmsPermission;
//import com.yqz.pth.service.UmsAdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author zzh
// * @Description
// * @date 2020-09-07 23:02
// */
//@Service
//@Transactional
//public class UmsAdminServiceImpl implements UmsAdminService {
//    @Autowired
//    private UmsAdminMapper adminMapper;
//    @Autowired
//    private UmsAdminRoleRelationDao adminRoleRelationDao;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UmsAdmin getAdminByUsername(String username) {
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
//        if (adminList != null && adminList.size() > 0) {
//            return adminList.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public List<UmsPermission> getPermissionList(Long adminId) {
//        return adminRoleRelationDao.getPermissionList(adminId);
//    }
//
//    @Override
//    public UmsAdmin register(UmsAdmin umsAdmin) {
//        umsAdmin.setCreateTime(new Date());
//        umsAdmin.setStatus(1);
//        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
//        adminMapper.insertSelective(umsAdmin);
//        return umsAdmin;
//    }
//}
