package com.adidas.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import org.springframework.stereotype.Component;



import java.io.IOException;
@Component
public class TraceFilter extends OncePerRequestFilter {

    private static final String X_CORRELATION_ID = "x-correlation-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)  throws ServletException, IOException {
        if ((request.getHeader(X_CORRELATION_ID) != null)){
            response.addHeader(X_CORRELATION_ID, request.getHeader(X_CORRELATION_ID));
        }
        filterChain.doFilter(request, response);
    }
}