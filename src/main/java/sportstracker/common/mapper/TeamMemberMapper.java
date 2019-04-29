package sportstracker.common.mapper;

import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Profile;
import sportstracker.model.TeamMemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Profile and account Data transfer
 */
@Mapper(componentModel = "spring")
public interface TeamMemberMapper {
    @Mappings({

            @Mapping(source = "profile.imageLink",target = "imageLink"),
            @Mapping(source = "profile.fullName",target = "fullName"),
            @Mapping(source = "account.accountId",target = "accountId")
    }
    )
    TeamMemberDto accountAndProfileToTeamMemberDto(Account account, Profile profile);
}
