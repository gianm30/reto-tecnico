package com.ntt.data.reto.tecnico.bean;

import com.ntt.data.reto.tecnico.exception.DesencriptacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class EncriptacionBean {
    @Autowired
    private SecretKey key;

    @Autowired
    private IvParameterSpec iv;

    private final String algoritmo = "AES/CBC/PKCS5Padding";

    @Bean
    public static SecretKey generateSecretKey() {
        KeyGenerator keyGenerator = null;

        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        keyGenerator.init(256);//128, 192, 256
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    @Bean
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public String encriptar(String input) {
        try {
            Cipher cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] cipherText = cipher.doFinal(input.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                IllegalBlockSizeException | InvalidKeyException |
                UnsupportedEncodingException e) {
            throw new DesencriptacionException(e);
        } catch(IllegalArgumentException e) {
            throw new DesencriptacionException("Valor no está encriptado");
        } catch(BadPaddingException e) {
            throw new DesencriptacionException("Llave expirada");
        }
    }

    public String desencriptar(String input) {
        try {
            if(input.contains(" "))
                input = input.replace(" ", "+");

            Cipher cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] bytes = input.getBytes("UTF-8");
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(bytes));
            return new String(plainText);
        } catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException |
                IllegalBlockSizeException | InvalidKeyException |
                UnsupportedEncodingException e) {
            throw new DesencriptacionException(e);
        } catch(IllegalArgumentException e) {
            throw new DesencriptacionException("Valor no está encriptado");
        } catch(BadPaddingException e) {
            throw new DesencriptacionException("Llave expirada");
        }
    }
}
