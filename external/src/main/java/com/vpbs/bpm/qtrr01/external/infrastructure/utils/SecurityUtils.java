package com.vpbs.bpm.qtrr01.external.infrastructure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpbs.bpm.qtrr01.external.infrastructure.dto.XUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;

import java.util.Base64;

public class SecurityUtils {

    public static final String HEADER_USER_NAME = "preferred_username";
    public static final String LANG_KEY = "lang_key";
    public static final String HEADER_USER_ID = "user_id";
    public static final String TEST_ROLE = "BS.LOG.002";
    public static final String HEADER_ISSUER = "iss";
    private static final String REALM_ACCESS = "realm_access";
    private static final String ROLES = "roles";


    @SneakyThrows
    public static XUserInfo getXUserInfo(String encodedToken) {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1].replace('-', '+').replace('_', '/');
        String jsonString = new String(Base64.getDecoder().decode(b64payload), "UTF-8");
        XUserInfo XUserInfo = new ObjectMapper().readValue(jsonString, XUserInfo.class);
        return XUserInfo;
    }


    public static String getUsername(String token) {
        XUserInfo xUserInfo = getXUserInfo(token);
        return xUserInfo != null ? xUserInfo.getPreferredUsername() : null;
    }

    public static String getAccessToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Authorization").substring("Bearer ".length());
    }
}
