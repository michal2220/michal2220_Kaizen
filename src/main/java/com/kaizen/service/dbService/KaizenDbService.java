package com.kaizen.service.dbService;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.User;
import com.kaizen.service.repository.KaizenRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
