package com.fiap.tech.challenge.global.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@UtilityClass
public class HttpUtil {

    public boolean hasCurrentRequest() {
        try {
            return ValidationUtil.isNotNull(RequestContextHolder.getRequestAttributes());
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(attributes).getRequest();
    }
}
