package sportstracker.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * Implementation of GrantedAuthority.
 * Simpler than Spring Security's SimpleGrantedAuthority
 * and easily use :).
 *
 * @author Chuc Ba Hieu
 */
public class SpectreGrantedAuthority implements GrantedAuthority {

    private String authority;

    public SpectreGrantedAuthority() {
    }

    public SpectreGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
