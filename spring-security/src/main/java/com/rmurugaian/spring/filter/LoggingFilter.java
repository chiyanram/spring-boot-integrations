package com.rmurugaian.spring.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author rmurugaian 2018-04-06
 */
public class LoggingFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
        final ServletRequest request, final ServletResponse response, final FilterChain chain) throws
        IOException,
        ServletException {

        System.out.println(request.getContentType());

        chain.doFilter(request, response);

        System.out.println(response.getContentType());

    }

    @Override
    public void destroy() {

    }
}
