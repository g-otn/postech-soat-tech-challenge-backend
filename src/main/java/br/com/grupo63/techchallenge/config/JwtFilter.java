package br.com.grupo63.techchallenge.config;

import br.com.grupo63.techchallenge.TechchallengeApplication;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtService jwtService;
    private static Logger logger = LoggerFactory.getLogger(TechchallengeApplication.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
            logger.info("Auth header: " + authHeader);
            if (!StringUtils.hasLength(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
                throw new GeneralSecurityException("Missing or invalid authorization header");
            }
            String jwt = authHeader.substring(7);
            Claims claims = jwtService.getClaims(jwt);
            request.setAttribute("clientId", claims.get("sub"));
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing or incorrect JWT Token.");
            logger.info("Unauthorized: " + e.getMessage());
        }
    }
}
