package com.picketing.www.application.filter.encoding.password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoder {

	private final String salt;
	private final String SALT_ENV_KEY = "PASSWORD_SALT";
	private final String ALGORITHM = "SHA-512";

	private final Long iteration;

    public PasswordEncoder(String salt) {
        this.salt = salt;
        this.iteration = Long.valueOf(salt.length());
        if (this.salt == null) {
            throw new IllegalArgumentException(SALT_ENV_KEY + " environment variable is not set");
        }
    }

	/**
	 * Kerckhoffs's principle 에 근거하여 암호화 알고리즘 및 iteration 은 공개 대상이 됩니다.
	 *
	 * @param plainTextPassword
	 * @return
	 */
	public String encode(String plainTextPassword) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(this.ALGORITHM);
			String saltedPassword = plainTextPassword + this.salt;
			byte[] hashedBytes = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
			for (long i = 0; i < this.iteration; i++) {
				hashedBytes = messageDigest.digest(hashedBytes);
			}
			return bytesToHex(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte aByte : bytes) {
			stringBuilder.append(String.format("%02x", aByte));
		}
		return stringBuilder.toString();
	}
}
