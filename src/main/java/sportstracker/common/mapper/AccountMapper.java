package sportstracker.common.mapper;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Profile;
import sportstracker.model.ProfileAccountDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Map account to profileAccountDto
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({@Mapping(source = "profile.imageLink",target = "imageLink")})
    ProfileAccountDto profileAndAcountToProfileAccountDto(Account account, Profile profile);

}