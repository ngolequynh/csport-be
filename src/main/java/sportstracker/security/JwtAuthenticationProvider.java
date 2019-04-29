package sportstracker.security;

import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import sportstracker.common.SportsTrackerUtils;
import sportstracker.dao.entity.Account;
import sportstracker.service.AccountService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *Authentication provider for JWT token authentication
 *
 * @author Chuc Ba Hieu
 */
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtService jwtService;
    private AccountService accountService;

    public JwtAuthenticationProvider(JwtService jwtService, AccountService accountService) {
        this.jwtService = jwtService;
        this.accountService = accountService;
        log.debug("Created");
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        log.debug("Authenticating ...");

        String token = (String) auth.getCredentials();

        JWTClaimsSet claims = jwtService.parseToken(token, JwtService.AUTH_AUDIENCE);

        String username = claims.getSubject();
        Account user = accountService.findUserByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        log.debug("User found ...");

        SportsTrackerUtils.ensureCredentialsUpToDate(claims, user);
        SpectrePrincipal principal = new SpectrePrincipal(user.toAccountDto());

        return new JwtAuthenticationToken(principal, token, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
