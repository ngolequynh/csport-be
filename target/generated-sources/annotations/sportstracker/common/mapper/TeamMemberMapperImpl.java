package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Profile;
import sportstracker.model.TeamMemberDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TeamMemberMapperImpl implements TeamMemberMapper {

    @Override
    public TeamMemberDto accountAndProfileToTeamMemberDto(Account account, Profile profile) {
        if ( account == null && profile == null ) {
            return null;
        }

        TeamMemberDto teamMemberDto = new TeamMemberDto();

        if ( account != null ) {
            teamMemberDto.setAccountId( account.getAccountId() );
        }
        if ( profile != null ) {
            teamMemberDto.setImageLink( profile.getImageLink() );
            teamMemberDto.setFullName( profile.getFullName() );
        }

        return teamMemberDto;
    }
}
