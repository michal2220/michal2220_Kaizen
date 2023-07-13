package com.kaizen.domain.dto;

import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class KaizenDto {
    private int kaizenId;
    private LocalDate fillingDate;
    private boolean completed;
    private LocalDate completionDate;
    private String problem;
    private String solution;
    private boolean rewarded;
    private int userId;
    private Integer rewardId;

    public KaizenDto(int kaizenId, LocalDate fillingDate,
                     boolean completed, LocalDate completionDate,
                     String problem, String solution, boolean rewarded,
                     int userId, Integer rewardId) {
        this.kaizenId = kaizenId;
        this.fillingDate = fillingDate;
        this.completed = completed;
        this.completionDate = completionDate;
        this.problem = problem;
        this.solution = solution;
        this.rewarded = rewarded;
        this.userId = userId;
        this.rewardId = rewardId;
    }
}
