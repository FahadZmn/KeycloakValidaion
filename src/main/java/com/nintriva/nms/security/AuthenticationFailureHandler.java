    package com.nintriva.nms.security;

    import org.springframework.http.HttpStatus;
    import org.springframework.security.core.AuthenticationException;

    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;

    public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse,
                                            AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.setStatus( HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.sendRedirect("/login?loginError=true");
        }
    }