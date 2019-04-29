package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Team;
import sportstracker.model.TeamDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TeamMapperImpl implements TeamMapper {

    @Override
    public TeamDto teamToTeamDto(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamDto teamDto = new TeamDto();

        teamDto.setTeamId( team.getTeamId() );
        teamDto.setName( team.getName() );
        teamDto.setCreatedDate( team.getCreatedDate() );
        teamDto.setLastModifiedDate( team.getLastModifiedDate() );
        teamDto.setHostId( team.getHostId() );
        teamDto.setActive( team.isActive() );

        return teamDto;
    }

    @Override
    public void updateTeamFromTeamDto(TeamDto teamDto, Team team) {
        if ( teamDto == null ) {
            return;
        }

        team.setName( teamDto.getName() );
        team.setHostId( teamDto.getHostId() );
        team.setCreatedDate( teamDto.getCreatedDate() );
        team.setActive( teamDto.isActive() );
        team.setLastModifiedDate( teamDto.getLastModifiedDate() );
    }
}
