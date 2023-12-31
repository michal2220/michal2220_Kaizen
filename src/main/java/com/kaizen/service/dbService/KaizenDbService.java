package com.kaizen.service.dbService;

import com.kaizen.controller.exception.KaizenNotFoundException;
import com.kaizen.domain.Kaizen;
import com.kaizen.service.repository.KaizenRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class KaizenDbService {
    private final KaizenRepository kaizenRepository;

    public Kaizen getKaizen(final int kaizenId) throws KaizenNotFoundException {
        return kaizenRepository.findByKaizenId(kaizenId).orElseThrow(KaizenNotFoundException::new);
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
