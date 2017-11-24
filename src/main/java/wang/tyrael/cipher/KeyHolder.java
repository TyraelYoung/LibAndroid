package wang.tyrael.cipher;

import android.security.KeyStoreParameter;
import android.security.keystore.KeyProperties;
import android.security.keystore.KeyProtection;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import wang.tyrael.log.LogHelper;

/**
 * Created by 王超 on 2017/11/24.
 */

public class KeyHolder {
    private static final String TAG = "KeyHolder";
    private final KeyStore keyStore;
    private final String algorithm = "AES";
    private final String storeType = "AndroidKeyStore";

    public KeyHolder() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        keyStore = KeyStore.getInstance(storeType);
        keyStore.load(null);
    }

    public SecretKey getKey(String keyName, AlgorithmParameterSpec params){
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
//            return generateAndStoreKey(keyName, params);
            String key = "jfjdnaieiqmngxxzpkjhrfgy";
            SecretKeySpec dks = null;
            try {
                dks = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return dks;
        }else{
            SecretKey secretKey = null;
            try {
                secretKey = (SecretKey) keyStore.getKey(keyName, null);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            }
            if(secretKey == null){
                System.out.println("CipherSupport:secretKey == null");
                generateAndStoreKey(keyName, params);
            }
            try {
                secretKey = (SecretKey) keyStore.getKey(keyName, null);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            }
            LogHelper.i(TAG, "CipherSupport:getKey:secretKey;" + secretKey);
            return secretKey;
        }
    }

    /**
     * 生成key 并存储到 AndroidKeyStore中
     */
    private SecretKey generateAndStoreKey(String keyName, AlgorithmParameterSpec params){
        try {
            KeyGenerator keyGenerator = null;
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
                //该版本AndroidKeyStore不支持AES,手动存储
                return null;
            }else{
                //应该是有自动存储
                keyGenerator = KeyGenerator.getInstance(algorithm, storeType);
                keyGenerator.init(params);
                SecretKey secretKey = keyGenerator.generateKey();
                return secretKey;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("Failed to create a symmetric key", e);
        }
    }
}
