package sportstracker.common.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import sportstracker.dao.entity.Account;
import sportstracker.dao.entity.Activity;
import sportstracker.model.ActivityDto;
import sportstracker.model.ActivityInputDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ActivityMapperImpl implements ActivityMapper {

    @Override
    public ActivityDto activityToActivityDto(Activity Activity) {
        if ( Activity == null ) {
            return null;
        }

        ActivityDto activityDto = new ActivityDto();

        String accountId = activityAccountAccountId( Activity );
        if ( accountId != null ) {
            activityDto.setAccountId( accountId );
        }
        activityDto.setActivityId( Activity.getActivityId() );
        activityDto.setTitle( Activity.getTitle() );
        activityDto.setLocation( Activity.getLocation() );
        activityDto.setDistance( Activity.getDistance() );
        activityDto.setWorkoutType( Activity.getWorkoutType() );
        activityDto.setDuration( Activity.getDuration() );
        if ( Activity.getActivityDate() != null ) {
            activityDto.setActivityDate( new SimpleDateFormat().format( Activity.getActivityDate() ) );
        }
        activityDto.setImageLink( Activity.getImageLink() );
        activityDto.setCreatedDate( Activity.getCreatedDate() );
        activityDto.setLastModifiedDate( Activity.getLastModifiedDate() );
        activityDto.setActivityType( Activity.getActivityType() );

        return activityDto;
    }

    @Override
    public void updateActivityFromActivityInputDto(ActivityInputDto activityInputDto, Activity activity) throws ParseException {
        if ( activityInputDto == null ) {
            return;
        }

        activity.setTitle( activityInputDto.getTitle() );
        activity.setLocation( activityInputDto.getLocation() );
        activity.setDuration( activityInputDto.getDuration() );
        activity.setWorkoutType( activityInputDto.getWorkoutType() );
        activity.setDistance( activityInputDto.getDistance() );
        if ( activityInputDto.getActivityDate() != null ) {
            activity.setActivityDate( new SimpleDateFormat().parse( activityInputDto.getActivityDate() ) );
        }
        activity.setActivityType( activityInputDto.getActivityType() );
    }

    private String activityAccountAccountId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        Account account = activity.getAccount();
        if ( account == null ) {
            return null;
        }
        String accountId = account.getAccountId();
        if ( accountId == null ) {
            return null;
        }
        return accountId;
    }
}
