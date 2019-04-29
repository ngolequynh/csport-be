package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Profile;
import sportstracker.model.ProfileAccountDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public ProfileAccountDto profileAndAcountToProfileAccountDto(Account account, Profile profile) {
        if ( account == null && profile == null ) {
            return null;
        }

        ProfileAccountDto profileAccountDto = new ProfileAccountDto();

        if ( account != null ) {
            profileAccountDto.setAccountId( account.getAccountId() );
            profileAccountDto.setEmail( account.getEmail() );
        }
        if ( profile != null ) {
            profileAccountDto.setImageLink( profile.getImageLink() );
            profileAccountDto.setFullName( profile.getFullName() );
        }

        return profileAccountDto;
    }
}
