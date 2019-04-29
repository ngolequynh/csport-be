package sportstracker.service;

import sportstracker.common.exception.EntityNotFoundException;
import sportstracker.common.exception.InvalidFileException;
import sportstracker.model.ActivityDto;
import sportstracker.model.ActivityInputDto;
import sportstracker.model.ActivityType;
import sportstracker.model.UserStatisticDto;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

/**
 * Activity interface
 *
 */
public interface ActivityService {

    List<ActivityDto> getActivitiesByUserOrderByDate(String userID) throws EntityNotFoundException;

    void updateActivityStatus(String activityId) throws EntityNotFoundException;

    void createActivity(ActivityInputDto activityInputDto, MultipartFile activityImage) throws EntityNotFoundException, InvalidFileException, ParseException;

    void updateActivity(ActivityInputDto activityInputUpdateDto, MultipartFile activityImage) throws EntityNotFoundException, InvalidFileException, ParseException;

    ActivityType[] getActivityType();

    UserStatisticDto getUserStatistic(String accountId) throws EntityNotFoundException;

    List<String> getSuggestionByAccount(String accountId) throws EntityNotFoundException;
}
