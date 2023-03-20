package com.miniproject.miniprojectgroupthree.util;

import com.miniproject.miniprojectgroupthree.domain.Member;
import com.miniproject.miniprojectgroupthree.error.AES256EncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;


public class AES256Encoder {
    public static String alg = "AES/CBC/PKCS5Padding";
    private final String key = "12345678910111213";
    private final String iv = key.substring(0, 16); // 16byte

    public String encodeString(String text) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes(UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new AES256EncodingException(e);
        }
    }

    public String decodeString(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new AES256EncodingException(e);
        }
    }


    public Member encodeMember(Member member) {

        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            member.setAccount(Base64.getEncoder().encodeToString(cipher.doFinal(member.getAccount().getBytes(UTF_8))));
            member.setName(Base64.getEncoder().encodeToString(cipher.doFinal(member.getName().getBytes(UTF_8))));
            member.setPhoneNumber(Base64.getEncoder().encodeToString(cipher.doFinal(member.getPhoneNumber().getBytes(UTF_8))));
            member.setBirth(Base64.getEncoder().encodeToString(cipher.doFinal(member.getBirth().getBytes(UTF_8))));

            return member;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new AES256EncodingException(e);
        }
    }

    public Member decodeMember(Member member) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            member.setAccount(new String(cipher.doFinal(Base64.getDecoder().decode(member.getAccount())), UTF_8));
            member.setName(new String(cipher.doFinal(Base64.getDecoder().decode(member.getName())), UTF_8));
            member.setPhoneNumber(new String(cipher.doFinal(Base64.getDecoder().decode(member.getPhoneNumber())), UTF_8));
            member.setBirth(new String(cipher.doFinal(Base64.getDecoder().decode(member.getBirth())), UTF_8));

            return member;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new AES256EncodingException(e);
        }
    }
}
