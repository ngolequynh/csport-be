package sportstracker.service;

import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.common.exception.InvalidFileException;
import sportstracker.dao.entity.Profile;
import sportstracker.model.ProfileDto;
import sportstracker.model.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {
    ProfileDto getProfileByAccountId(String accountId);

    List<ProfileDto> getAllUsersByFullName(String fullName);

    ProfileDto getOneProfileByAccountId(String accountId);
    void saveProfile(Profile profile);

    List<ProfileDto> getAllUsersByFullNameNotContainAccount(String fullName, String accountId);

    void updateProfile(ProfileDto profileDto, MultipartFile profileImage) throws EntityNotFoundException, InvalidFileException;

    Status[] getStatus();

    String  resetToDefaultImage(String accountId);
}