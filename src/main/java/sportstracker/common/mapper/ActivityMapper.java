package sportstracker.common.mapper;

import sportstracker.dao.entity.Activity;
import sportstracker.model.ActivityDto;
import sportstracker.model.ActivityInputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;

/**
 * Map activity to activityDto
 *
 */
@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    @Mapping(source = "account.accountId", target = "accountId")
    ActivityDto activityToActivityDto(Activity Activity);

    @Mapping(target = "activityId", ignore = true)
    void updateActivityFromActivityInputDto(ActivityInputDto activityInputDto, @MappingTarget Activity activity) throws ParseException;

}
