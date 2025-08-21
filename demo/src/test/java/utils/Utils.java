package utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorConfig;

public class Utils {
    public static String generateOTP(String secretKey) {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        // Optionally you can configure it (like window size, etc.)
        GoogleAuthenticatorConfig config = new GoogleAuthenticatorConfig();
        gAuth = new GoogleAuthenticator(config);
        int otp = gAuth.getTotpPassword(secretKey); // Generates the OTP
        return String.valueOf(otp);
    }
}
