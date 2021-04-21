package com.yqz.pth.service;

import com.yqz.pth.common.api.CommonResult;

/**
 * @author zzh
 * @Description
 * @date 2020-09-06 21:42
 */
public interface MemberService {
    CommonResult generateAuthCode(String telPhone);

    CommonResult verifyAuthCode(String telPhone, String authCode);
}
