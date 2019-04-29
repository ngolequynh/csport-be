package sportstracker.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ActivityStatisticsDto {

    String activityTypeName;

    List<ActivityDataDto> activityDataDTOList;

    boolean isActive = false;

    public ActivityStatisticsDto(String activityTypeName) {
        this.activityTypeName = activityTypeName;
        activityDataDTOList = new ArrayList<>();
    }

}


