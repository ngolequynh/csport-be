package sportstracker.common.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Profile;
import sportstracker.model.ProfileDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDto profileToProfileDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        String accountId = profileAccountAccountId( profile );
        if ( accountId != null ) {
            profileDto.setAccountId( accountId );
        }
        profileDto.setProfileId( profile.getProfileId() );
        profileDto.setFullName( profile.getFullName() );
        profileDto.setImageLink( profile.getImageLink() );
        profileDto.setIntroduction( profile.getIntroduction() );
        profileDto.setHobbies( profile.getHobbies() );
        profileDto.setStatus( profile.getStatus() );
        profileDto.setCreatedDate( profile.getCreatedDate() );
        profileDto.setLastModifiedDate( profile.getLastModifiedDate() );

        return profileDto;
    }

    @Override
    public Profile profileDtoToProfile(ProfileDto profileDto) {
        if ( profileDto == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setProfileId( profileDto.getProfileId() );
        profile.setFullName( profileDto.getFullName() );
        profile.setImageLink( profileDto.getImageLink() );
        profile.setCreatedDate( profileDto.getCreatedDate() );
        profile.setLastModifiedDate( profileDto.getLastModifiedDate() );
        profile.setIntroduction( profileDto.getIntroduction() );
        profile.setHobbies( profileDto.getHobbies() );
        profile.setStatus( profileDto.getStatus() );

        return profile;
    }

    @Override
    public void updateProfileFromProfileDto(ProfileDto profileDto, Profile profile) {
        if ( profileDto == null ) {
            return;
        }

        profile.setProfileId( profileDto.getProfileId() );
        profile.setFullName( profileDto.getFullName() );
        profile.setImageLink( profileDto.getImageLink() );
        profile.setCreatedDate( profileDto.getCreatedDate() );
        profile.setLastModifiedDate( profileDto.getLastModifiedDate() );
        profile.setIntroduction( profileDto.getIntroduction() );
        profile.setHobbies( profileDto.getHobbies() );
        profile.setStatus( profileDto.getStatus() );
        if ( profile.getAccount() == null ) {
            profile.setAccount( new Account() );
        }
        profileDtoToAccount( profileDto, profile.getAccount() );
    }

    private String profileAccountAccountId(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        Account account = profile.getAccount();
        if ( account == null ) {
            return null;
        }
        String accountId = account.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        return accountId;
    }

    protected void profileDtoToAccount(ProfileDto profileDto, Account mappingTarget) {
        if ( profileDto == null ) {
            return;
        }
    }
}
