package sportstracker.model;

import lombok.Data;

/**
 * user statistic dto
 *
 */
@Data
public class UserStatisticDto {
    private Double runningDisTotal;
    private Double cyclingDisTotal;
    private Double gymTimeTotal;
    private Double meditationTimeTotal;
    private Double climbingTimeTotal;
    private Double skatingTimeTotal;
    private Double swimmingTimeTotal;
    private Double yogaTimeTotal;
    private Double hikingTimeTotal;
}
