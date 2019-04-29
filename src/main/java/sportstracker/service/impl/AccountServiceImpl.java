package sportstracker.service.impl;

import lombok.extern.slf4j.Slf4j;
import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.common.mapper.AccountMapper;
import sportstracker.common.util.TimeIntervalUtil;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Activity;
import sportstracker.dao.entity.Profile;
import sportstracker.dao.repository.AccountRepository;
import sportstracker.dao.repository.ActivityRepository;
import sportstracker.dao.repository.ProfileRepository;
import sportstracker.model.*;
import sportstracker.security.SpectrePrincipal;
import sportstracker.service.AccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static sportstracker.common.util.TimeIntervalUtil.getClientCurrentLocalDateFromDate;
import static sportstracker.common.util.TimeUtil.*;

/**
 * Implement interface Account Service
 *
 */

@Slf4j
@Service
@Qualifier("AccountService")
public class AccountServiceImpl implements AccountService {

    private AccountMapper accountMapper;
    private AccountRepository accountRepository;
    private ProfileRepository profileRepository;
    private ActivityRepository activityRepository;

    public AccountServiceImpl(AccountMapper accountMapper,
                              AccountRepository accountRepository,
                              ProfileRepository profileRepository,
                              ActivityRepository activityRepository) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
        this.activityRepository = activityRepository;
    }

    /**
     * Get list of profileAccountDto
     *
     * @param username user_name
     * @return ist of profileAccountDto
     *
     */
    @Override
    public List<ProfileAccountDto> getAllUsersByUserName(String username) {
        List<Account> accounts = accountRepository.findAllByUserNameContainingIgnoreCase(username.toLowerCase());
        List<Profile> profiles = new ArrayList<>();
        accounts.forEach(account -> profiles.add(profileRepository.findProfileByAccount_AccountId(account.getAccountId())));
        return IntStream.range(0, accounts.size())
                .mapToObj(i -> accountMapper.profileAndAcountToProfileAccountDto(accounts.get(i), profiles.get(i)))
                .collect(Collectors.toList());
    }

    /**
     * Find account by social id
     *
     * @param socialId social id
     * @return optional account object
     */
    @Override
    public Optional<Account> findUserBySocialId(String socialId) {
        return accountRepository.findAccountBySocialId(socialId);
    }

    /**
     * Find account by username
     *
     * @param userName username
     * @return  optional account object
     */
    @Override
    public Optional<Account> findUserByUserName(String userName) {
        return accountRepository.findAccountByUserName(userName);
    }

    @Override
    public Optional<Account> findUserByUserId(String userId) {
        return accountRepository.findAccountByAccountId(userId);
    }

    @Override
    public ProfileAccountDto findProfileUserByUserId(String userId) {
        Account account = accountRepository.getAccountByAccountId(userId);
        Profile profile = profileRepository.findProfileByAccount_AccountId(userId);
        return accountMapper.profileAndAcountToProfileAccountDto(account, profile);
    }

    /**
     * Save account to database
     *
     * @param account account data
     */
    @Override
    public void saveUser(Account account) {
        accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user having username: " + username);

        // delegates to findUserByUsername
        Account user = findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        log.debug("Loaded user having username: " + username);

        return new SpectrePrincipal(user.toAccountDto());
    }

    /**
     * * Get User statistic
     *
     * @param accountId account id
     * @param timeInterval time interval
     * @param timeZoneOffset time zone offset
     * @return user statistic
     * @throws EntityNotFoundException throws Exception if account not found in database
     */
    @Override
    public IndividualUserStatisticDto getUserStatistic(String accountId, String timeInterval, String timeZoneOffset) throws EntityNotFoundException {
        int activeDayTotal;
        double activityTimeTotal = 0.0;
        double runningDistanceTotal = 0.0;
        double cyclingDistanceTotal = 0.0;
        double cyclingTimeTotal = 0.0;
        double runningTimeTotal = 0.0;
        double meditationTimeTotal = 0.0;
        double climbingTimeTotal = 0.0;
        double skatingTimeTotal = 0.0;
        double swimmingTimeTotal = 0.0;
        double yogaTimeTotal = 0.0;
        double hikingTimeTotal = 0.0;
        double gymTimeTotal = 0.0;
        Date registerDate;
        int runningRating;
        int runningRank;
        int totalActiveRunningMember;

        int clientTimeZoneOffset = Integer.parseInt(timeZoneOffset);
        TimeInterval timeIntervalValue = TimeInterval.values()[Integer.parseInt(timeInterval)];
        Date endDate = getClientCurrentLocalTime(clientTimeZoneOffset);
        Date beginDate = getClientCurrentLocalDateFromDate(timeIntervalValue, clientTimeZoneOffset);

        Account account = accountRepository.getAccountByAccountId(accountId);
        IndividualUserStatisticDto individualUserStatisticDto = new IndividualUserStatisticDto();
        if (account == null) {
            throw new EntityNotFoundException(Account.class, "accountId");
        } else {
            activeDayTotal = activityRepository.getNumberOfDaysEngageActivity(accountId, beginDate, endDate);
            registerDate = serverLocalTimeToClientLocalTime(account.getCreatedDate(), clientTimeZoneOffset);
            try {
                runningRating = activityRepository.getUserRunningRating(accountId, beginDate, endDate);
                individualUserStatisticDto.setRunningDistanceRating(runningRating);
            } catch (Exception e) {
                individualUserStatisticDto.setRunningDistanceRating(0);
            }
            try {
                runningRank = activityRepository.getUserRunningRank(accountId, beginDate, endDate);
                totalActiveRunningMember = accountRepository.getNumberOfActiveRunningMembers(beginDate, endDate);
                individualUserStatisticDto.setRunningRank(String.valueOf(runningRank) + "/" + String.valueOf(totalActiveRunningMember));
            } catch (Exception e) {
                individualUserStatisticDto.setRunningRank("-");
            }
            for (Activity activity : activityRepository.findActivitiesByAccountAndActivityDateBetweenAndIsActive(account, beginDate, endDate, true)) {
                activityTimeTotal += activity.getDuration();
                switch (activity.getActivityType()) {
                    case Running:
                        runningDistanceTotal += activity.getDistance();
                        runningTimeTotal += activity.getDuration();
                        break;
                    case Cycling:
                        cyclingDistanceTotal += activity.getDistance();
                        cyclingTimeTotal += activity.getDuration();
                        break;
                    case Climbing:
                        climbingTimeTotal += activity.getDuration();
                        break;
                    case Skating:
                        skatingTimeTotal += activity.getDuration();
                        break;
                    case Swimming:
                        swimmingTimeTotal += activity.getDuration();
                        break;
                    case Gym:
                        gymTimeTotal += activity.getDuration();
                        break;
                    case Hiking:
                        hikingTimeTotal += activity.getDuration();
                        break;
                    case Yoga:
                        yogaTimeTotal += activity.getDuration();
                        break;
                    case Meditation:
                        meditationTimeTotal += activity.getDuration();
                        break;
                }
            }
        }
        individualUserStatisticDto.setActiveDayTotal(activeDayTotal);
        individualUserStatisticDto.setBeginDate(dateToString(beginDate));
        individualUserStatisticDto.setEndDate(dateToString(endDate));
        individualUserStatisticDto.setRegisterDate(dateToString(registerDate));
        individualUserStatisticDto.setRunningDistanceTotal(runningDistanceTotal);
        individualUserStatisticDto.setCyclingDistanceTotal(cyclingDistanceTotal);
        individualUserStatisticDto.setClimbingTimeTotal(climbingTimeTotal);
        individualUserStatisticDto.setHikingTimeTotal(hikingTimeTotal);
        individualUserStatisticDto.setSkatingTimeTotal(skatingTimeTotal);
        individualUserStatisticDto.setSwimmingTimeTotal(swimmingTimeTotal);
        individualUserStatisticDto.setYogaTimeTotal(yogaTimeTotal);
        individualUserStatisticDto.setMeditationTimeTotal(meditationTimeTotal);
        individualUserStatisticDto.setGymTimeTotal(gymTimeTotal);
        individualUserStatisticDto.setActivityTimeTotal(activityTimeTotal);
        individualUserStatisticDto.setRunningTimeTotal(runningTimeTotal);
        individualUserStatisticDto.setCyclingTimeTotal(cyclingTimeTotal);

        return individualUserStatisticDto;
    }

    @Override
    public
    List<ActivityStatisticsDto> getUserGraphicalStatistic(String accountId,
                                                          String timeInterval,
                                                          String timeZoneOffset) throws EntityNotFoundException {

        List<ActivityStatisticsDto> list = new ArrayList<>();
        int clientTimeZoneOffset = Integer.parseInt(timeZoneOffset);
        TimeInterval timeIntervalValue = TimeInterval.values()[Integer.parseInt(timeInterval)];
        Date endDate = getClientCurrentLocalTime(clientTimeZoneOffset);
        Date beginDate = getClientCurrentLocalDateFromDate(timeIntervalValue, clientTimeZoneOffset);
        List<Date> dateList;

        Account account = accountRepository.getAccountByAccountId(accountId);
        IndividualUserStatisticDto individualUserStatisticDto = new IndividualUserStatisticDto();
        dateList = TimeIntervalUtil.getDateListWithTimeInterval(timeIntervalValue, clientTimeZoneOffset, account.getCreatedDate());
        //List<ActivityType> activityTypeList = Arrays.asList(ActivityType.values());
        List<String> activityTypeList = Stream.of(ActivityType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if (account == null) {
            throw new EntityNotFoundException(Account.class, "accountId");
        } else {

            for (String activityTypeName : activityTypeList ) {
                list.add(new ActivityStatisticsDto(activityTypeName));
            }
            List<Activity> activityList = activityRepository.findActivitiesByAccountAndActivityDateBetweenAndIsActive(account, beginDate, endDate, true);
            Stream.of(ActivityType.values()).collect(Collectors.toList()).stream().forEach(activityType -> {
                dateList.stream().filter(date -> date != dateList.get(dateList.size() - 1)).forEachOrdered(date -> {

                    double totalDuration = activityList.stream()
                            .filter(activity -> activity.getActivityType() == activityType && activity.getActivityDate().after(date) && activity.getActivityDate().before(dateList.get(dateList.indexOf(date) + 1))).mapToDouble(Activity::getDuration).sum() / 3600;
                    double totalDistance = 0.0;
                    boolean isActive = false;
                    if (activityType != ActivityType.Yoga && activityType != ActivityType.Gym && activityType != ActivityType.Meditation) {
                        totalDistance = activityList.stream()
                                .filter(activity -> activity.getActivityType() == activityType && activity.getActivityDate().after(date) && activity.getActivityDate().before(dateList.get(dateList.indexOf(date) + 1)))
                                .mapToDouble(Activity::getDistance).sum();
                    }
                    if (totalDistance != 0 || totalDuration !=0) {
                        isActive = true;
                        list.stream().filter(listABC -> listABC.getActivityTypeName().equals(activityType.name())).limit(1).findFirst().get().setActive(isActive);
                    }
                    if (activityType == ActivityType.Climbing) {
                        list.stream().filter(listABC -> listABC.getActivityTypeName().equals(activityType.name())).limit(1).findFirst().get().getActivityDataDTOList().add(new ActivityDataDto(totalDistance, totalDuration * 60, dateToShortString(date)));
                        return;
                    }
                    list.stream().filter(listABC -> listABC.getActivityTypeName().equals(activityType.name())).limit(1).findFirst().get().getActivityDataDTOList().add(new ActivityDataDto(totalDistance, totalDuration, dateToShortString(date)));

                });
            });
        }
        return list;
    }

}