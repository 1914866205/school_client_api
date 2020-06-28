package com.niit.soft.client.api.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName AliPayConfig
 * @Description TODO
 * @Author 田震
 * @Date 2020/6/1
 **/
@Configuration
public class AliPayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 按照我文章图上的信息填写
    public static String app_id = "2016102200735792";
//    public static String app_id = "2088922236175791";


    // 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写

//    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCG7slJdFZ26rXA1+ri9kMJeVAhg6yzoFi21ALcPH/zFzKKuvbavl2kYcgsm/a6Jn64OQA9fErBRch+pvtdU9kwtHxn6Mz3Eisa709hkoNMOhPVSyNr3bvseIqmP/LWVg24gvQ05SNdYTxh1DidsRo2hFAHPtb9MMxp3vAov1mirb+MPNNoqC2u68I/9RvYuBGkNg8SvrFpSuZjzw0sLeaWAuXv9SFDFBgSP1ibF0kx1D0T9ygvsnt4sF0riyZMiGS7m+Dy1snLjbBe7zBXzJ9c6Oi9vnUdqu6XQkDYioeFQmCcpLzwIP9mo70LvKzdA8hzAC04eGoP9hEeVwXTw3xzAgMBAAECggEAESvKCcL4TeHIh2Xyyjc0iLwbamhrTeCV6Y5JXuWEEtdc4zKvv16CZCzdYG/okf/cGG2IjkqG6NmMjjGJw3KsLPqK6l5B1x9ExQS3QV93vfeWFoqdU1jAVXNxkX6J1KTW0ig4E5MlUPjSylBLdCXZhB6KYKGqkkXnfYGVZloTehQa8mdtlLze8ELjUx32f2y7KWnlG3wFoPRZXLdRu/VkNPJg/acuomx5ycbtKUf40BMNylZP4xhaW0TZ/cgZwC5SzzH8HGR3By7b5KqWCM8WSb4pkGYMSRCQWXanfgOQDPoXZXlJGikV6GB3iYkcfywPludcn6gCTZpbakKCZ4jkYQKBgQDDqoTGGhNb5wz0rHyoCw1Dq7xjoD8P5ZUcdicJ1bI4RYHN8b1etqVY21kqaPco/76ApyFuh1vlxJ0nSP3GyNexXl4rHuNysre9EoMgwWYFQW+la3ZwvWnmzuQz+Mtec1SnAFiKv/Y3rxfs+yU+dYw5jvbi139FcEVRvf8E6DB3BwKBgQCwihpHIdBBejdXZgBx60gCNbYAP0OOdA5duZNu6rUjQPRTio1UfJ7+YL7sQXA87NcPYgbkdxhpoujvU9DLblLtJdeTvpGfq/5ATBm21bjFTRBOSwyR2QuTsRHGAieVl7HrOkv+g5dwnqvchu7pi6un2OU1C39n+0PugW+Oq6BoNQKBgGiEKj4jaZnatmnvxPqCRMiT8KKK04SrTtYQKtQvCO7T+f1HFq+EApiPOnbT8Mo+qcTRAaOnvCfkuUllzOElIUoGLWj9K+N1oX3maJLTm6pNBNUorPCVzP7UHsPALum500sNfT5IQb4G0lHs6T0OzyNjwW70S3CNAwoh4hn96P17AoGBAJICNoecTLzK0xii0T1m5dCGEbgUmtAn5nXX9TsoPZYhs7GghxFYXLtw0pdd6/FHxPsBDUhJw9Qim9rNDIQkA8wFn02fsJtJyeftyNqx2ra77P+EDKpXw75sFZIZ2BleNBWjypR+y3KmmOe0Zcac8OTKe4+qqb4bT2zkepqaEfn9AoGAZog0CbtJfKgCo5cx2gdPodOQmmAltGkKVepKBdy1CGDpjyTaAEgYAR6gVKN58hMET5gj7WrrM/uYPRKm7Z1avAcqBtyFGdEtD+ZPK/uR790Azt27ZJgcwkJhOrvA7wH6E+EGYCQt7mgpeyvzwxppG+kBMf1IZ5PxfAuVb7knT6o

    //    public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCbcKDceiSoZyzXR+tC0/zlZesV6YHFViiDP0lzVrk2tpU2yY/+znq8M26dRS/2THuKUeaC7aGSUXzAoDgfvwiqQp6qxo/0nYK/bVnC1ucvBkyb9MnK0DCDSfrmModGwF4WfjEDL9DvU7HTagID5qjrdgZz6LIQlUFrm7ecS1hSwSxbnx6+1EO4VihemazGOK4lCBQ16hVjPIfrHEwzR9VW3zbXjw8hHHG3Q26FrwaL03l4ZG8C6Cux3kMp+dOGitQBFMwLjr+GNzxDyArsT+h7LNgrMCvf2ZQ4plHlidqPOizlNP4cprB2yccmf2CmVztl5LZVQgI4OYPzvx02INVZAgMBAAECggEAGr8lnuqRljyytZSVgdlSYfLIoeudPeSWcgcYz3Tzx9thD51dXr4O1oWPGhbGqv38IA/5tzvGmpPVAJxRK/1GM23lOYOTT8cusVvSnzk1bJ0L36Yedvyd7Gl1d9jn+B+v0PUD4AWBUT04kyVhlWRCWsnjUFyRD1Nvf2G7FXTNjOeUlnOhmqRACwDRsSLhxSNwmBaLFL9i27F3k/ZjW+kgorlgNXCS8HWIFupLqAcObQAH43OOWXMaWf3NoxTe8tP1HD8BRcacl6tnLrC1ElcGMJ1yrEJ2hzvYa8qSuB2cOYuuy2ljYlLzIXvvYJEFqnSrTcaNt2nNRBVCbmPRzhjVgQKBgQDyF6Y9iEVFvFNQLvSCNvtppYl7CNm+BEfaEAS3U2+nn0JSj19okvhQ5IuMiGb3kH5NQKT+3eyH8tPGIKcmiPVkjF/WjmxZXVrihBiko6Tk9Pl3t8sQKJI5ln10kizfqdjgdzu+AYF8GCtk+pBsaKimWxGp6Gqn5f2WI7jigKZAsQKBgQCkXp5HSyyoglYkQif+oerkNfZu99eHinGTA27E4WCIEiT8dnqCmJKrhDtgimALE25OKulfGIhSnEg4mGVy58UniMWh6I74TYAYxrPJSDwJ9PHa3JDheMwBFgDailxEwwNDUwX3uAZdvmpsgw0St7cfjdu1jIUo1eNtshk/l25JKQKBgDx1mYe+c4Zh7PCWeWvXrVwKAlaetDpXOVaFL5hVFwpmpJqXwb0ND0Ssfwleu1BSXqiOX2ZjKAIfq7HMT9I43Af7Yqfjs71xqhPt53WehLzFTVQPq3/ikfod0kD9jIVjFo2gWWQvXhYOty+lv9HDJXM+RSAIsjIyJu2LCE5Q0LRRAoGBAIlsVjbTIiZMukqcSSX6KBHfEAddDYTT+frpDs5qhDOfv+6X+/t+JnpiFuazKnN4LmwKMo2ATFGNOlCfEYqNPme8UUf50LbGzQdp8dyimRfWA/NxH2xze1SZGeOddGFFDlBWdJHU+PWdgwrwUpjIosEt8HiMYLRwhzIqijOBnwJpAoGBAKF1G5Nzzx7Zan/Hm/KEr3drDvUlZQOU1e/tIQZE3JEnHRwK3rfXsCNMSBqb6+d0FPP6oys1cnupQD9c1lU4UhIyBrxoe/hkoseBceDc4WhpBjcgZ4tYdUjTd1ppDEtGeXq1nUJBaE7f1yD45gSfbM1cQKlnXjr3WMJH8IjirNka";
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCG7slJdFZ26rXA1+ri9kMJeVAhg6yzoFi21ALcPH/zFzKKuvbavl2kYcgsm/a6Jn64OQA9fErBRch+pvtdU9kwtHxn6Mz3Eisa709hkoNMOhPVSyNr3bvseIqmP/LWVg24gvQ05SNdYTxh1DidsRo2hFAHPtb9MMxp3vAov1mirb+MPNNoqC2u68I/9RvYuBGkNg8SvrFpSuZjzw0sLeaWAuXv9SFDFBgSP1ibF0kx1D0T9ygvsnt4sF0riyZMiGS7m+Dy1snLjbBe7zBXzJ9c6Oi9vnUdqu6XQkDYioeFQmCcpLzwIP9mo70LvKzdA8hzAC04eGoP9hEeVwXTw3xzAgMBAAECggEAESvKCcL4TeHIh2Xyyjc0iLwbamhrTeCV6Y5JXuWEEtdc4zKvv16CZCzdYG/okf/cGG2IjkqG6NmMjjGJw3KsLPqK6l5B1x9ExQS3QV93vfeWFoqdU1jAVXNxkX6J1KTW0ig4E5MlUPjSylBLdCXZhB6KYKGqkkXnfYGVZloTehQa8mdtlLze8ELjUx32f2y7KWnlG3wFoPRZXLdRu/VkNPJg/acuomx5ycbtKUf40BMNylZP4xhaW0TZ/cgZwC5SzzH8HGR3By7b5KqWCM8WSb4pkGYMSRCQWXanfgOQDPoXZXlJGikV6GB3iYkcfywPludcn6gCTZpbakKCZ4jkYQKBgQDDqoTGGhNb5wz0rHyoCw1Dq7xjoD8P5ZUcdicJ1bI4RYHN8b1etqVY21kqaPco/76ApyFuh1vlxJ0nSP3GyNexXl4rHuNysre9EoMgwWYFQW+la3ZwvWnmzuQz+Mtec1SnAFiKv/Y3rxfs+yU+dYw5jvbi139FcEVRvf8E6DB3BwKBgQCwihpHIdBBejdXZgBx60gCNbYAP0OOdA5duZNu6rUjQPRTio1UfJ7+YL7sQXA87NcPYgbkdxhpoujvU9DLblLtJdeTvpGfq/5ATBm21bjFTRBOSwyR2QuTsRHGAieVl7HrOkv+g5dwnqvchu7pi6un2OU1C39n+0PugW+Oq6BoNQKBgGiEKj4jaZnatmnvxPqCRMiT8KKK04SrTtYQKtQvCO7T+f1HFq+EApiPOnbT8Mo+qcTRAaOnvCfkuUllzOElIUoGLWj9K+N1oX3maJLTm6pNBNUorPCVzP7UHsPALum500sNfT5IQb4G0lHs6T0OzyNjwW70S3CNAwoh4hn96P17AoGBAJICNoecTLzK0xii0T1m5dCGEbgUmtAn5nXX9TsoPZYhs7GghxFYXLtw0pdd6/FHxPsBDUhJw9Qim9rNDIQkA8wFn02fsJtJyeftyNqx2ra77P+EDKpXw75sFZIZ2BleNBWjypR+y3KmmOe0Zcac8OTKe4+qqb4bT2zkepqaEfn9AoGAZog0CbtJfKgCo5cx2gdPodOQmmAltGkKVepKBdy1CGDpjyTaAEgYAR6gVKN58hMET5gj7WrrM/uYPRKm7Z1avAcqBtyFGdEtD+ZPK/uR790Azt27ZJgcwkJhOrvA7wH6E+EGYCQt7mgpeyvzwxppG+kBMf1IZ5PxfAuVb7knT6o=";

    // 支付宝公钥,对应APPID下的支付宝公钥。 按照我文章图上的信息填写支付宝公钥，别填成商户公钥

    //    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm3Cg3HokqGcs10frQtP85WXrFemBxVYogz9Jc1a5NraVNsmP/s56vDNunUUv9kx7ilHmgu2hklF8wKA4H78IqkKeqsaP9J2Cv21ZwtbnLwZMm/TJytAwg0n65jKHRsBeFn4xAy/Q71Ox02oCA+ao63YGc+iyEJVBa5u3nEtYUsEsW58evtRDuFYoXpmsxjiuJQgUNeoVYzyH6xxMM0fVVt82148PIRxxt0Nuha8Gi9N5eGRvAugrsd5DKfnThorUARTMC46/hjc8Q8gK7E/oeyzYKzAr39mUOKZR5Ynajzos5TT+HKawdsnHJn9gplc7ZeS2VUICODmD878dNiDVWQIDAQAB";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhu7JSXRWduq1wNfq4vZDCXlQIYOss6BYttQC3Dx/8xcyirr22r5dpGHILJv2uiZ+uDkAPXxKwUXIfqb7XVPZMLR8Z+jM9xIrGu9PYZKDTDoT1Usja9277HiKpj/y1lYNuIL0NOUjXWE8YdQ4nbEaNoRQBz7W/TDMad7wKL9Zoq2/jDzTaKgtruvCP/Ub2LgRpDYPEr6xaUrmY88NLC3mlgLl7/UhQxQYEj9YmxdJMdQ9E/coL7J7eLBdK4smTIhku5vg8tbJy42wXu8wV8yfXOjovb51Harul0JA2IqHhUJgnKS88CD/ZqO9C7ys3QPIcwAtOHhqD/YRHlcF08N8cwIDAQAB";

    /**
     * App
     */
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url = "*****************************************";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url = "*****************************************";
    /**
     * 网页
     */
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url1 = "************************************";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url1 = "*************************************";
    /**
     * 移动h5
     */
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url2 = "http://192.168.43.180:8088/#/metrocard";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url2 = "http://192.168.43.180:8088/#/metrocard";


    //    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}