package test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import org.apache.commons.codec.binary.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.StringUtils;
import test.AESUtil;

/**
 * 对微信小程序用户加密数据的解密
 * 创建者 柒
 * 创建时间 2018年3月12日
 */
public class WXBizDataCrypt {

    public static String illegalAesKey = "-41001";//非法密钥
    public static String illegalIv = "-41002";//非法初始向量
    public static String illegalBuffer = "-41003";//非法密文
    public static String decodeBase64Error = "-41004"; //解码错误
    public static String noData = "-41005"; //数据不正确

    private String appid;

    private String sessionKey;

    public WXBizDataCrypt(String appid, String sessionKey) {
        this.appid = appid;
        this.sessionKey = sessionKey;
    }

    /**
     * 检验数据的真实性，并且获取解密后的明文.
     * @param encryptedData  string 加密的用户数据
     * @param iv  string 与用户数据一同返回的初始向量
     * @return data string 解密后的原文
     * @return String 返回用户信息
     */
    public String decryptData(String encryptedData, String iv) {
        if (sessionKey.length() != 24) {
            return illegalAesKey;
        }
        // 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
        byte[] aesKey = Base64.decodeBase64(sessionKey);

        if (iv.length() != 24) {
            return illegalIv;
        }
        // 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
        byte[] aesIV = Base64.decodeBase64(iv);

        // 对称解密的目标密文为 Base64_Decode(encryptedData)
        byte[] aesCipher = Base64.decodeBase64(encryptedData);

        try {
            byte[] resultByte = AESUtil.decrypt(aesCipher, aesKey, aesIV);
            if (null != resultByte && resultByte.length > 0) {
                String userInfo = new String(resultByte, "UTF-8");
                JSONObject jsons = JSON.parseObject(userInfo);
                String id = jsons.getJSONObject("watermark").getString("appid");
                if (!StringUtils.equals(id, appid)) {
                    return illegalBuffer;
                }
                return userInfo;
            } else {
                return noData;
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * encryptedData 和 iv 两个参数通过小程序wx.getUserInfo()方法获取
     * @param args
     * @see
     */
    public static void main(String[] args) {
        String appId = "wxb0e02258c4ceb6ce";
        String sessionKey = "0sokneDwbetMzluLPYAn3A==";
        String encryptedData = "oHSWIPoscFgKX9IgTe3n2Ua6CFDXnFTFJ89YLiHH/Pu52wNqnw2ZV9yBN3x1jInlShnmhJZ5UIwaJri7etWxv9GQHhzd08aH300z0SejhNkgWScvqH3oGayx/li9Ug+hRd5Qr6eyNjlpGhKVN2tmqT1X8fkQarxVrKzAFcTBEfZ68maR8Be7rDg+faDEzAis0vnqFiDJJ1WfhWkZFC8WHg==";
        String iv = "CRASDcRrlMSULrIw3lffYQ==";

        WXBizDataCrypt biz = new WXBizDataCrypt(appId, sessionKey);

        System.out.println(biz.decryptData(encryptedData, iv));

    }
}