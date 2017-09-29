package com.qunar.fresh2017.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author lihuiminli
 * @create 2017-06-26 12:05
 */
@Slf4j
public class AESUtil {
    private static final String AES = "AES";//加解密方法
    private static final String CRYPT_KEY = "YUUAtestYUUAtest";//加解密使用的字符串

    /**
     * 加密
     *
     * @param src 待加密的字节数组
     * @param key 用于加密的字符串
     * @return 加密后字节数组
     */
    private static byte[] encrypt(byte[] src, String key) {
        Cipher cipher;
        byte[] encryptByteArray = new byte[]{};

        try {
            cipher = Cipher.getInstance(AES);
            SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
            cipher.init(Cipher.ENCRYPT_MODE, securekey);//设置密钥和加密形式
            encryptByteArray = cipher.doFinal(src);
        } catch (Exception e) {
            log.error("加密时出错！", e);
        }

        return encryptByteArray;
    }


    /**
     * 解密
     *
     * @param src 待解密的字节数组
     * @param key 用于解密的字符串
     * @return 解密后的字节数组
     */
    private static byte[] decrypt(byte[] src, String key){
        Cipher cipher;
        byte[] decryptByteArray = new byte[]{};
        try {
            cipher = Cipher.getInstance(AES);
            SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);//设置加密Key
            cipher.init(Cipher.DECRYPT_MODE, securekey);//设置密钥和解密形式
            decryptByteArray = cipher.doFinal(src);
        }catch (Exception e){
            log.error("解密时出错！", e);
        }
        return decryptByteArray;
    }

    /**
     * 解密
     *
     * @param data 待解密字符串
     * @return 解密后的字符串
     */
    static String decrypt(String data) {
        try {
            return new String(decrypt((Hex.decodeHex(data.toCharArray())),
                    CRYPT_KEY));
        } catch (Exception e) {
            log.error("解密时出错！", e);
        }
        return null;
    }

    /**
     * 加密
     *
     * @param data 待加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String data) {
        try {
            return Hex.encodeHexString(encrypt(data.getBytes(), CRYPT_KEY));
        } catch (Exception e) {
            log.error("加密时出错！", e);
        }
        return null;
    }
}

