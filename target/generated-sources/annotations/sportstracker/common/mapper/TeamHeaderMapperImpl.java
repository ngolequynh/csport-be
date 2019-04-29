package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Profile;
import sportstracker.dao.entity.Team;
import sportstracker.model.TeamHeaderDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TeamHeaderMapperImpl implements TeamHeaderMapper {

    @Override
    public TeamHeaderDto teamAndProfileDtoToTeamHeaderDto(Team team, Profile profile) {
        if ( team == null && profile == null ) {
            return null;
        }

        TeamHeaderDto teamHeaderDto = new TeamHeaderDto();

        if ( team != null ) {
            teamHeaderDto.setName( team.getName() );
            teamHeaderDto.setHostId( team.getHostId() );
        }
        if ( profile != null ) {
            teamHeaderDto.setImageLink( profile.getImageLink() );
            teamHeaderDto.setFullName( profile.getFullName() );
        }

        return teamHeaderDto;
    }
}
