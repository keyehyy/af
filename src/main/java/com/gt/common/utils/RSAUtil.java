package com.gt.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @desc RSA加解密工具类
 * @author zhukeyan
 * @date 2022/4/21
 */

public class RSAUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtFXy4V+KG80UdeMxsMaKp7jxsxcGWrTwtb76GOeR5h9GUJ/vFN0I224PhN2LOwJ+y0aE8nrDbKZ8H8GVQkGfKzEdeoRmhkr/JsR4GTz0J+O3cwdSrq7ZP1whcMsnxEMpn8c3cEgYbKDGvAAXDytQ7QerWT25q+sgBrebcM00xOBKASuUMG/ks/K9th3+FJGFl/WJsS+I+A2WPlrrBzXdsppz/63mVXMThvLp0X8QH7DXkyDQmShvgdyY/yR6TaecXGPn3s+OtrBItwrodsGN3lWJ78IIoR9GfzggWYjuyjlJ/hjEb/vSH6ZJQXrI/wxMj0NmfRpvrkAFQZg0UzsMIwIDAQAB";
    public static String privateKey ="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC0VfLhX4obzRR14zGwxoqnuPGzFwZatPC1vvoY55HmH0ZQn+8U3Qjbbg+E3Ys7An7LRoTyesNspnwfwZVCQZ8rMR16hGaGSv8mxHgZPPQn47dzB1Kurtk/XCFwyyfEQymfxzdwSBhsoMa8ABcPK1DtB6tZPbmr6yAGt5twzTTE4EoBK5Qwb+Sz8r22Hf4UkYWX9YmxL4j4DZY+WusHNd2ymnP/reZVcxOG8unRfxAfsNeTINCZKG+B3Jj/JHpNp5xcY+fez462sEi3Cuh2wY3eVYnvwgihH0Z/OCBZiO7KOUn+GMRv+9IfpklBesj/DEyPQ2Z9Gm+uQAVBmDRTOwwjAgMBAAECggEBALE0T/FUn3LIJ9XbxcVOo4tCegcglfbmHC6LlliInN6DVuX88sMOnBh7YzaNOLZosk1vY06egZAvXIMcB15O+2hG4A+3MMO/yE/A+b7bHZRg+LBIvt+2IvZWwumCR/ZchPdjnD9yKMX9iqvMbtxSFTiFs8OCmwOrjRHstAq5KqRVlwwxEKve0A3WyjkkfmSQ/kwZAsA2HJLiUoMlTzbvcY+GqsDQ8O//csG2yAmrdiNRDT9XMxzquvuhL3V5Qy+GOmQ5nijRyhdRz569qi4eGMvDiAPC6bLYv3Pdu9gpWqjXws0WwWSPJQ8rqN70ATEtYDNqPV444J53uoJAzYvQ5JECgYEA3Xjri0KWlGNc39q00DAvPvdqbzY5PWRVlNs/BnBf/hoxAvAN1uU+sXnMTzavpGtOMh8wYc9PJSeBO8W9Z4imtJt3cx80SI/NN9VQpy3jnZKW1T/VroPnS0rAifRnYmO6WGKenMJ4jp8L+DV52dDxvlmYuO+GqaWyeodXzjKz75sCgYEA0HM+oNGD/W7SUEObPK8E3+5WTyasER0QHE0tqy2a1+xeGGNPCdVfUwQ2XdyTAZI5vTQIJajwR46UIkXq78TDbMdJto5y/1tZKnjVFoo9AJlKzByraTmSdsauzezeNoPY4m00E/Sad/I2LkLk2Uu5i/n1Cw5mr/IbgUQ//ofLUhkCgYEAsd2ABcqHlkqqdxl1Z1GbUA1yDDjXBYgD91WRK0Gqxi8Qt5RE+RYJII152RBJRVnsZfKIb7UHkgZFEtT/BRxJDflhmDIyUigzHykQ6gK/r3ff7QmkOdCzO9OgB+sh8HG5YWxqh+ji21++RCvmXVkiHMZmEfhEBREO4rq5eN9dhQsCgYAhodaDAAr5wv2v8ZiEbACvWLcYQjcU88l7p4As1Ejdececy7ke7wxYSp+y+TnG0R77xf9ihCVwhjKkbP+A/BBD5suf5s/rH4P9dE19O4SoKhOFZ0LI0X8uJtRhaUxZI2DE97WlWpvpqmzPff+Kh8tCKGw/Am3TCY8ihi6FFMfbUQKBgQCfXEu4jrezBDlTA+o2ummoVIJ72nGOqWDSi6Ptc5bKT5S2IT9RJYWyp6t5xyH2531Do6hb5LDvwpSm2tz475WhtX/L1iYZvYzFHUw5dlN+M8PwIQlLDPiHzpg2jcM3XRHGYMCyq+CT9JpVVJqW+0x9cIAzDa5cUS788WLLb84i5w==";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 2048;

    //获得公钥字符串
    public static String getPublicKeyStr(Map<String, Object> keyMap) throws Exception {
        //获得map中的公钥对象 转为key对象
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }


    //获得私钥字符串
    public static String getPrivateKeyStr(Map<String, Object> keyMap) throws Exception {
        //获得map中的私钥对象 转为key对象
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        //编码返回字符串
        return encryptBASE64(key.getEncoded());
    }

    //获取公钥
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    //获取私钥
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }


    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    //***************************签名和验证*******************************
    public static byte[] sign(byte[] data, String privateKeyStr) throws Exception {
        PrivateKey priK = getPrivateKey(privateKeyStr);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(priK);
        sig.update(data);
        return sig.sign();
    }

    public static boolean verify(byte[] data, byte[] sign, String publicKeyStr) throws Exception {
        PublicKey pubK = getPublicKey(publicKeyStr);
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(sign);
    }

    //************************加密解密**************************
    public static byte[] encrypt(byte[] plainText, String publicKeyStr) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = plainText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        int i = 0;
        byte[] cache;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(plainText, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(plainText, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptText = out.toByteArray();
        out.close();
        return encryptText;
    }

    public static byte[] decrypt(byte[] encryptText, String privateKeyStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLen = encryptText.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptText, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptText, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] plainText = out.toByteArray();
        out.close();
        return plainText;
    }


    public static void main(String[] args) {
        Map<String, Object> keyMap;
        byte[] cipherText;
        String input = "Hello World!";
        try {
            keyMap = initKey();
            //String publicKey = getPublicKeyStr(keyMap);
            System.out.println("公钥------------------");
            System.out.println(publicKey);
            //String privateKey = getPrivateKeyStr(keyMap);
            System.out.println("私钥------------------");
            System.out.println(privateKey);

            System.out.println("测试可行性-------------------");
            System.out.println("明文=======" + input);

            cipherText = encrypt(input.getBytes(), publicKey);
            //加密后的东西
            System.out.println("密文=======" + new String(cipherText));
            //开始解密
            byte[] plainText = decrypt(cipherText, privateKey);
            System.out.println("解密后明文===== " + new String(plainText));
            System.out.println("验证签名-----------");

            TreeMap map = new TreeMap(new MComparator());
            map.put("age", 20);
            map.put("name", "张三");
            String str = JSONObject.toJSONString(map);
            System.out.println("\n原文:" + str);
            byte[] signature = sign(str.getBytes(), privateKey);
            System.out.println(Base64.encodeBase64String(signature));
            boolean status = verify(str.getBytes(), signature, publicKey);
            System.out.println("验证情况：" + status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    static class MComparator implements Comparator {
        public int compare(Object obj1, Object obj2) {
            String ele1 = (String) obj1;
            String ele2 = (String) obj2;
            return ele2.compareTo(ele1);
        }
    }

}