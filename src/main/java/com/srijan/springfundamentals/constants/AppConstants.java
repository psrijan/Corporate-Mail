package com.srijan.springfundamentals.constants;

public class AppConstants {
    public class MailConstants {
        public static final String NAME = "name";
        public static final String SUBJECT_TAG = "subject";
        public static final String LOGO = "appLogo";
        public static final String APPLICATION_NAME = "appName";
        public static final String THEME_COLOR = "themeColor";
        public static final String DATE = "date";
        public static final String SENDER_NAME = "senderName";
        public static final String EVENT_NAME = "festivalName";
        public static final String BIRTHDAY_OF = "birthdayOf";
    }

    public interface SecurityConstants {
        long EXPIRATION_TIME = 864_000_000; // 10 days
        String TOKEN_PREFIX = "Bearer ";
        String SECURITY_HEADER_STRING = "Authorization";
        String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
        String APP_NAME = "Topup Admin Server";
        String AUTH_URL_REGEX = ".*/auth[/]{0,1}";
        String TOTP_REGEX = ".*/auth/verify/2fa[/]{0,1}";
    }

    public interface ApplicationCodes {
        String SUCCESS = "0";
        String TOTP_REQUIRED = "-1";
        String VALIDATION_AUTHENTICATION_FAILURE = "1";
        String AUTHORIZATION_FAILURE = "2";
        String RESOURCE_CREATION_FAILURE = "3";
        String INTERNAL_SERVER_ERROR = "4";
        String RESOURCE_NOT_AVAILABLE = "5";
        String RESOURCE_ALREADY_EXISTS = "6";
        String CLIENT_ERROR = "7";
        String CONFLICT = "8";
    }


}
