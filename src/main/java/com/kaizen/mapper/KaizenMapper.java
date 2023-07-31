package com.kaizen.mapper;

import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.controller.exception.UserNotFoundException;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.RewardDbService;
import com.kaizen.service.dbService.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KaizenMapper {

    private final UserDbService userDbService;
    private final RewardDbService rewardDbService;

    public KaizenDto mapToKaizenDto(final Kaizen kaizen){
        return new KaizenDto(
                kaizen.getKaizenId(),
                kaizen.getFillingDate(),
                kaizen.isCompleted(),
                kaizen.getCompletionDate(),
                kaizen.getProblem(),
                kaizen.getSolution(),
                kaizen.isRewarded(),
                kaizen.getUser().getUserId(),
                kaizen.getReward() == null ? null : kaizen.getReward().getRewardId()
        );
    }

    public Kaizen mapToKaizen(final KaizenDto kaizenDto) throws UserNotFoundException {
        try {
            return new Kaizen(
                    kaizenDto.getKaizenId(),
                    kaizenDto.getFillingDate(),
                    kaizenDto.isCompleted(),
                    kaizenDto.getCompletionDate(),
                    kaizenDto.getProblem(),
                    kaizenDto.getSolution(),
                    kaizenDto.isRewarded(),
                    userDbService.getUser(kaizenDto.getUserId()),
                    rewardDbService.getReward(kaizenDto.getRewardId()) == null ? null : rewardDbService.getReward(kaizenDto.getRewardId())
            );
        } catch (NullPointerException e) {
            return new Kaizen(
                    kaizenDto.getKaizenId(),
                    kaizenDto.getFillingDate(),
                    kaizenDto.isCompleted(),
                    kaizenDto.getCompletionDate(),
                    kaizenDto.getProblem(),
                    kaizenDto.getSolution(),
                    kaizenDto.isRewarded(),
                    userDbService.getUser(kaizenDto.getUserId())
            );
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public List<KaizenDto> mapToKaizenDtoList(final List<Kaizen> kaizens) {
        return kaizens.stream()
                .map(this::mapToKaizenDto)
                .collect(Collectors.toList());
    }
}
