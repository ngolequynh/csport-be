package sportstracker.model;

import lombok.Data;

/**
 * Account Data transfer
 *
 */
@Data
public class TeamMemberDto {
    private String accountId;
    private String fullName;
    private String imageLink;
}
