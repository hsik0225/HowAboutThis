package com.naru.howaboutthis.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 요청과 응답의 인코딩 값을 UTF-8로 변경하기 위한 클래스(기본 = ISO-8859-1)
 */
@Component
public class CharsetEncodingFilter implements Filter {

    private static final String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
