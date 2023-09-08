package me.saniukvyacheslav.util.security;

import lombok.experimental.UtilityClass;
import me.saniukvyacheslav.util.MathUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Different cryptography utilities.
 * This utilities class defines helpful methods for working with cryptography.
 */
@UtilityClass
public final class CryptoUtils {

    /**
     * Generate random byte array.
     * @param aLength - length of result array.
     * @return - generated random byte array.
     */
    public static byte[] generateRandomByteArray(int aLength) {
        // Check argument:
        if (aLength < 0) throw new IllegalArgumentException("Capacity of array [aLength] must be greater or equal zero.");
        if (aLength == 0) return new byte[0];

        // Result array:
        byte[] arr = new byte[aLength];

        // Generate random bytes:
        for (int i=0; i<aLength; i++)
            arr[i] = (byte) MathUtils.generateRandomIntFromRange(-128, 128);

        return arr;
    }

    /**
     * Create new AES initialization vector.
     * @return AES initialization vector.
     */
    public static IvParameterSpec createInitializationVector() {
        return new IvParameterSpec(CryptoUtils.generateRandomByteArray(16));
    }

    /**
     * Generate random String of specified length.
     * @param aLength - a string length.
     * @return - random string.
     */
    public static String generateRandomString(int aLength) {
        // Check argument:
        if (aLength < 0) throw new IllegalArgumentException("Capacity of array [aLength] must be greater or equal zero.");
        if (aLength == 0) return "";

        byte[] arr = new byte[aLength];

        // Generate random bytes:
        for (int i=0; i<aLength; i++)
            arr[i] = (byte) MathUtils.generateRandomIntFromRange(97, 122);

        return new String(arr, StandardCharsets.UTF_8);
    }

    /**
     * Get AES secret key from String.
     * @param aDecodedStr - key string.
     * @return - secret key instance.
     */
    public static SecretKey aesKeyFromString(String aDecodedStr) {
        // Check parameters:
        if (aDecodedStr == null || aDecodedStr.isEmpty())
            throw new IllegalArgumentException("String [aDecodedStr] must be not null and not empty.");
        int length = aDecodedStr.length();
        if (length != 16 && length != 24 && length != 32)
            throw new IllegalArgumentException("String [aDecodedStr] must have 16, 24 or 32 length.");
        return new SecretKeySpec(aDecodedStr.getBytes(StandardCharsets.UTF_8), 0, aDecodedStr.length(), "AES");
    }
}
