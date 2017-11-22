package wang.tyrael.cipher;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.security.spec.AlgorithmParameterSpec;

import wang.tyrael.library.cipher.CipherSupport;
import wang.tyrael.library.cipher.EncryptData;

/**
 * Created by 王超 on 2017/11/21.
 */

public class AndroidCipher {
    private final CipherSupport cipherSupport;
    /**
     * If the user has unlocked the device Within the last this number of seconds,
     * it can be considered as an authenticator.
     */
    private static final int AUTHENTICATION_DURATION_SECONDS = 30;

    public AndroidCipher(CipherSupport cipherSupport) {
        this.cipherSupport = cipherSupport;
    }

    public AndroidCipher(String type, String keyName, AlgorithmParameterSpec params, String transformation, String algorithm) {
        this.cipherSupport = new CipherSupport(type, keyName, params, transformation, algorithm, new AndroidBase64());
    }

    /**
     * 各种参数采用默认值
     */
    public AndroidCipher(String keyName) {
        String type = "AndroidKeyStore";
        AlgorithmParameterSpec params = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            params = new KeyGenParameterSpec.Builder(keyName,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    //.setUserAuthenticationRequired(true)
                    // Require that the user has unlocked in the last 30 seconds
//                    .setUserAuthenticationValidityDurationSeconds(AUTHENTICATION_DURATION_SECONDS)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build();
        }
//        else{
//            params = new KeyGenParameterSpec.Builder(keyName,
//                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
//                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//                    .setUserAuthenticationRequired(true)
//                    // Require that the user has unlocked in the last 30 seconds
//                    .setUserAuthenticationValidityDurationSeconds(AUTHENTICATION_DURATION_SECONDS)
//                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
//                    .build();
//        }
        String transformation =  KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/"
                + KeyProperties.ENCRYPTION_PADDING_PKCS7;
        String algorithm = KeyProperties.KEY_ALGORITHM_AES;
        this.cipherSupport = new CipherSupport(type, keyName, params, transformation, algorithm, new AndroidBase64());
    }

    public EncryptData encrypt(String plaintext){
        return cipherSupport.encrypt(plaintext);
    }

    public String decrypt(EncryptData encryptData){
        return cipherSupport.decrypt(encryptData);
    }
}
