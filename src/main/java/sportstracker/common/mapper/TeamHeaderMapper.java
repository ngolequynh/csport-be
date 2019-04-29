package sportstracker.common.mapper;

import sportstracker.dao.entity.Profile;
import sportstracker.dao.entity.Team;

import sportstracker.model.TeamHeaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Map team header to teamHeaderDto
 *
 */
@Mapper(componentModel = "spring")
public interface TeamHeaderMapper {

    @Mappings({
            @Mapping(source = "profile.imageLink",target = "imageLink"),
            @Mapping(source = "profile.fullName",target = "fullName"),
            @Mapping(source = "team.name",target = "name"),
            @Mapping(source = "team.hostId",target = "hostId")
    })
    TeamHeaderDto teamAndProfileDtoToTeamHeaderDto(Team team, Profile profile);
}
