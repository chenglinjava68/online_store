package com.yuan.common;

import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributes implements ErrorAttributes {

    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();

        Integer status = getAttribute(requestAttributes, "javax.servlet.error.status_code");
        if (status == null) {
            errorAttributes.put("code", 999);
            errorAttributes.put("msg", "None");
        } else {
            errorAttributes.put("code", status);
            errorAttributes.put("msg", getDetail(status));
        }
        return errorAttributes;
    }

    private String getDetail(Integer status) {
        try {
            return HttpStatus.valueOf(status).getReasonPhrase();
        } catch (Exception e) {
            return status.toString();
        }
    }

    public Throwable getError(RequestAttributes requestAttributes) {
        return getAttribute(requestAttributes, "javax.servlet.error.exception");
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
