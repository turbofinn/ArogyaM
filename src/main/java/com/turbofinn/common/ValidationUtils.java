package com.turbofinn.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final int MOBILE_NUMBER_LENGTH = 10;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidMobileNumber(String mobileNo) {
        return mobileNo.length() == MOBILE_NUMBER_LENGTH;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        boolean hasDotAndAt = email.contains(".") && email.contains("@");
        return matcher.matches() && hasDotAndAt;
    }
}
