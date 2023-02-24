package com.example;

import com.example.service.RedisService;
import com.example.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeApplicationTests {
    @Autowired
    private TradeService tradeService;

    @Autowired
    private RedisService redisService;

    @Test
    public void Test01() throws Exception {
        tradeService.buyCurrent("btc", "usdt",  new BigDecimal("200.133435"), new BigDecimal("1.09"), "marlon1475");
    }

    @Test
    public void Test02() throws Exception {
        tradeService.buyCurrent("luna", "usdt",  new BigDecimal("200.133435"), new BigDecimal("1.09"), "marlon1475");
    }

    @Test
    public void Test03() throws Exception {
        System.out.println(tradeService.sellCurrent("btc", "usdt",  new BigDecimal("200.133435"), new BigDecimal("1.09"), "marlon1475"));
    }

    @Test
    public void Test04() throws Exception {
        tradeService.sellCurrent("luna", "usdt",  new BigDecimal("200.133435"), new BigDecimal("1.09"), "marlon1475");
    }

    @Test
    public void Test05() {
        redisService.set("key", "val");
        System.out.println(redisService.get("key"));
    }

    @Test
    public void test06() {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential("SecretId", "SecretKey");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-nanjing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+8613299531475"};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId("1400798125");
            req.setTemplateId("1712446");

            String[] templateParamSet1 = {"123456"};
            req.setTemplateParamSet(templateParamSet1);
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e);
        }
    }
}
