package sportstracker.common.mapper;

import sportstracker.dao.entity.Competition;
import sportstracker.model.CompetitionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Map competition to competition Dto
 *
 */
@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    @Mappings({
            @Mapping(source = "host.accountId",target = "hostId"),
            @Mapping(source = "invitee.accountId",target = "inviteeId")})
    CompetitionDto competitionToCompetitionDto(Competition competition);

    @Mappings({
            @Mapping(target = "competitionId", ignore = true),
            @Mapping(source = "hostId", target = "host.accountId"),
            @Mapping(source = "inviteeId", target = "invitee.accountId")
    })
    Competition competitionDtoToCompetition(CompetitionDto competitionDto, @MappingTarget Competition competition);
}