package sportstracker.security;

import lombok.extern.slf4j.Slf4j;
import sportstracker.common.property.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for token authentication
 *
 * @author Chuc Ba Hieu
 */
@Slf4j
public class SpectreTokenAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;
    private SecurityProperties properties;

    public SpectreTokenAuthenticationFilter(AuthenticationManager authenticationManager, SecurityProperties properties) {
        this.authenticationManager = authenticationManager;
        this.properties = properties;
        log.info("Created");
    }

    /**
     * Checks if a "Bearer " token is present
     */
    private boolean tokenPresent(HttpServletRequest request) {

        String header = request.getHeader(properties.getJwt().getTokenRequestHeaderName());
        return header != null && header.startsWith(properties.getJwt().getTokenPrefix());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.debug("Inside SpectreTokenAuthenticationFilter ...");

        if (tokenPresent(request)) {

            log.debug("Found a token");

            String token = request.getHeader(properties.getJwt().getTokenRequestHeaderName()).substring(7);
            JwtAuthenticationToken authRequest = new JwtAuthenticationToken(token);

            try {

                Authentication auth = authenticationManager.authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(auth);

                log.debug("Token authentication successful");

            } catch (Exception e) {

                log.debug("Token authentication failed - " + e.getMessage());

                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                        "Authentication Failed: " + e.getMessage());

                return;
            }

        } else

            log.debug("Token authentication skipped");

        filterChain.doFilter(request, response);
    }
}