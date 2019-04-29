package sportstracker.model;

import lombok.Data;

@Data
public class ActivityDataDto {

    private String activityDate;
    private double distance;

    private double duration;

    private double velocity;

    public ActivityDataDto(double distance, double duration, String activityDate) {
        this.distance = Math.ceil(distance * 10) / 10;
        this.duration = Math.ceil(duration * 10) / 10;
        this.activityDate = activityDate;
        if (duration != 0) this.velocity = Math.ceil((distance / duration) * 10) / 10;
    }
}
