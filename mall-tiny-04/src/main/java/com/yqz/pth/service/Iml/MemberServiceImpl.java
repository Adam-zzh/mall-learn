package com.yqz.pth.service.Iml;

import com.yqz.pth.common.api.CommonResult;
import com.yqz.pth.service.MemberService;
import com.yqz.pth.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author zzh
 * @Description
 * @date 2020-09-06 21:43
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String prefix;
    @Value ("${redis.key.expired.authCode}")
    private Long expire;

    @Override
    public CommonResult generateAuthCode(String telPhone) {
        StringBuilder sb = new StringBuilder();
        String authCode = null;
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        authCode = sb.toString();
        String key = prefix+telPhone;
        redisService.set(key, authCode);
        redisService.expire(key, expire);
        return new CommonResult().success(authCode,"获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String telPhone, String authCode) {
        String value = redisService.get(prefix + telPhone);
        if (!StringUtils.isEmpty(value)&&value.equals(authCode)){
            return  new CommonResult().success(null);
        }else {
            return new CommonResult().failed("短信验证码校验不通过");
        }
    }
}
