package com.srijan.springfundamentals.constants;

public class AppConstants {
    public interface MailConstants {
        String NAME = "name";
        String SUBJECT_TAG = "subject";
        String LOGO = "appLogo";
        String APPLICATION_NAME = "appName";
        String THEME_COLOR = "themeColor";
        String DATE = "date";
        String SENDER_NAME = "senderName";
        String EVENT_NAME = "festivalName";
        String BIRTHDAY_OF = "birthdayOf";
        String CUSTOMIZED_BODY = "customBody";
        String EMAIL = "email";
        String RELATION = "relation";
        String REMARKS = "remarks";
        String DATE_OF_BIRTH= "dateOfBirth";
    }

    public interface SecurityConstants {
        String TOKEN_PREFIX = "Bearer ";
        String SECURITY_HEADER_STRING = "Authorization";
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
