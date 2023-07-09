package com.kaizen.service.dbService;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.service.repository.KaizenRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class KaizenDbService {
    private final KaizenRepository kaizenRepository;

    public Kaizen getKaizen(final int kaizenId) {
        return kaizenRepository.findByKaizenId(kaizenId);
    }

    public List<Kaizen> getAllKaizens() {
        return kaizenRepository.findAll();
    }

    public List<Kaizen> getKaizenByUser(int userId) {
        return kaizenRepository.findKaizenByUserUserId(userId);
    }

    public List<Kaizen> getKaizenByReward(int rewardId) {
        return kaizenRepository.findKaizenByRewardRewardId(rewardId);
    }

    public Kaizen saveKaizen(final Kaizen kaizen) {
        return kaizenRepository.save(kaizen);
    }

    public void deleteKaizenById (final int kaizenId) {
        kaizenRepository.deleteById(kaizenId);
    }

    public List<Kaizen> kaizensOlderThen (final LocalDate date) {
        return kaizenRepository.findKaizenByFillingDateBefore(date);
    }

    public List<Kaizen> getKaizensCreatedBy(String name, String lastname) {
        return kaizenRepository.findKaizenByUserNameAndUserLastname(name,lastname);
    }
}
