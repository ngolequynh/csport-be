package sportstracker.service;

import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.dao.entity.Account;
import sportstracker.model.AccountStatisticDto;
import sportstracker.model.ActivityStatisticsDto;
import sportstracker.model.IndividualUserStatisticDto;
import sportstracker.model.ProfileAccountDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * Account interface
 *
 */
public interface AccountService extends UserDetailsService {
    List<ProfileAccountDto> getAllUsersByUserName(String username);
    Optional<Account> findUserBySocialId(String socialId);
    Optional<Account> findUserByUserName(String userName);
    Optional<Account> findUserByUserId(String userId);
    ProfileAccountDto findProfileUserByUserId(String userId);
    void saveUser(Account account);
    IndividualUserStatisticDto getUserStatistic(String accountId,
                                                String timeInterval,
                                                String timeZoneOffset) throws EntityNotFoundException;
    List<ActivityStatisticsDto> getUserGraphicalStatistic(String accountId,
                                                          String timeInterval,
                                                          String timeZoneOffset) throws EntityNotFoundException;
}