package com.common.filter;

import com.common.util.HttpsUtil;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * Created by sheying on 2018/06/11.
 * 主要实现: cookies默认禁用，https默认不校验
 */
public class CommonFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // 禁用cookie
        this.forbiddenCookie(request);
        // 关闭https证书验证
        HttpsURLConnection rs = HttpsUtil.CloseCertificateVerification(request);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 禁用cookie
     * @param request
     */
    private void forbiddenCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                cookie.setMaxAge(0); // 不记录cookie
            }
        }
    }

}
