package sportstracker.service;

import sportstracker.model.FacebookInformationDto;

public interface FacebookLoginService {
    void saveToDBIfUserNotRegistered(FacebookInformationDto facebookInformationDTO);

    String getAccountIdByFbUserId(String fbUserId);

}
