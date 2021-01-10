package com.example.demo.exception;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public interface HttpUtil {

  String UNKNOWN = "unknown";

  String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";

  String PROXY_CLIENT_IP = "Proxy-Client-IP";

  String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

  String REMORE_ADDRESS = "REMORE-ADDRESS";

  String UNEXPECTED_ERROR = "Unexpected error occurs while processing request";

  public static enum HttpStatus {

    BAD_REQUEST(400, "Request is not valid", ErrorCode.BAD_REQUEST),
    METHOD_NOT_ALLOWED(405, "Method not allowed", ErrorCode.METHOD_NOT_ALLOWED),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsuported media type", ErrorCode.UNSUPPORTED_MEDIA_TYPE),
    NOT_ACCEPTABLE(406, "Not acceptable", ErrorCode.NOT_ACCEPTABLE),
    NOT_FOUND(404, "Resource not found", ErrorCode.RESOURCE_NOT_EXIST),
    SERVICE_UNAVAILABLE(503, "Service is not available", ErrorCode.SERVICE_UNAVAILABLE),
    UNAUTHORIZE(401, "Service is not available", ErrorCode.UNAUTHORIZED);

    private final int statusCode;
    private final String error;
    private final ErrorCode errorCode;

    private static Map<Integer, HttpStatus> cacheByCode = new HashMap<>();

    static {
      for (HttpStatus e : HttpStatus.values()) {
        cacheByCode.put(e.getStatusCode(), e);
      }
    }

    public static HttpStatus getByCode(int statusCode) {
      return cacheByCode.get(statusCode);
    }

    /**
     * @param
     * @param error
     */
    private HttpStatus(int statusCode, String error, ErrorCode errorCode) {
      this.statusCode = statusCode;
      this.error = error;
      this.errorCode = errorCode;
    }

    public int getStatusCode() {
      return statusCode;
    }

    public String getError() {
      return error;
    }

    public ErrorCode getErrorCode() {
      return errorCode;
    }

  }

  public static String getErrorMessage(int statusCode) {
    HttpStatus httpStatus = HttpStatus.getByCode(statusCode);
    return httpStatus != null ? httpStatus.getError() : UNEXPECTED_ERROR;
  }

  public static ErrorCode getErrorCode(int statusCode) {
    HttpStatus httpStatus = HttpStatus.getByCode(statusCode);
    return httpStatus != null ? httpStatus.getErrorCode() : ErrorCode.UNEXPECTED_ERROR;
  }

  public static String getOriginalClientIp(HttpServletRequest request) {
    String source = X_FORWARDED_FOR_HEADER;
    String ip = getIpFromHeader(source, request);
    if (isEmptyIpAddress(ip)) {
      source = PROXY_CLIENT_IP;
      ip = getIpFromHeader(source, request);
    }
    if (isEmptyIpAddress(ip)) {
      source = WL_PROXY_CLIENT_IP;
      ip = getIpFromHeader(source, request);
    }
    if (isEmptyIpAddress(ip)) {
      source = REMORE_ADDRESS;
      ip = request.getRemoteAddr();
    }
    if (isEmptyIpAddress(ip) || ip.length() > 16) {
      String err = String.format("Client IP: %s, Source: %s - Not valid IP-Address!",
          ip, source);
      throw new RuntimeException(err);
    }
    return ip;
  }

  public static String getIpFromHeader(String header, HttpServletRequest request) {
    String headerValue = request.getHeader(header);
    String clientIP = null;
    if (!StringUtils.isEmpty(headerValue)) {
      String[] addresses = headerValue.split(",");
      if (addresses != null && addresses.length > 0) {
        clientIP = addresses[0];
      }
    }
    return clientIP == null ? null : clientIP.trim();
  }

  public static boolean isEmptyIpAddress(String ip) {
    return ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip);
  }


  @SuppressWarnings("unchecked")
  public static List<String> getRequestParameters(HttpServletRequest httpServletRequest) {
    Enumeration<String> formParams = httpServletRequest.getParameterNames();
    return Collections.list(formParams);
  }


}
