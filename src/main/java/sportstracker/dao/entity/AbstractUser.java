package sportstracker.dao.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
@MappedSuperclass
public class AbstractUser {

    public interface Role {
    }

    @Column(name = "password")
    protected String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "account_role", joinColumns = @JoinColumn(name="account_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    // A jWT issued before this won't be valid
    @Column(nullable = false)
    private long credentialsUpdated = System.currentTimeMillis();

}
