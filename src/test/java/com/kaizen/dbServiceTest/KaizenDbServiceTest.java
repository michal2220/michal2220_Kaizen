package com.kaizen.dbServiceTest;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.User;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.repository.KaizenRepository;
import com.kaizen.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KaizenDbServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KaizenRepository kaizenRepository;

    @Autowired
    private KaizenDbService kaizenDbService;

    @Test
    void getKaizenByUserId_OneKaizenTest() {
        //Given
        User user = new User("Krystyna", "Czubowna", 4);
        User user1 = new User("Michal", "Szpak", 3);

        userRepository.save(user);
        userRepository.save(user1);

        Kaizen kaizen = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);
        kaizenRepository.save(kaizen);

        kaizen.setUser(user);
        user.getKaizen().add(kaizen);

        userRepository.save(user);
        kaizenRepository.save(kaizen);

        //When
        List<Kaizen> kaizenListFromDb = kaizenDbService.getKaizenByUser(user.getUserId());

        //Then
        assertEquals(1, kaizenListFromDb.size());
        assertEquals(user.getUserId(),
                kaizenListFromDb.get(0).getUser().getUserId());

        //Clean up
        userRepository.deleteById(user.getUserId());
        userRepository.deleteById(user1.getUserId());
        kaizenRepository.deleteById(kaizen.getKaizenId());
    }

    @Test
    void getKaizenByUserId_MultipleKaizenTest() {
        //Given
        User user = new User("Krystyna", "Czubowna", 4);
        User user1 = new User("Michal", "Szpak", 3);

        userRepository.save(user);
        userRepository.save(user1);

        Kaizen kaizen = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);
        kaizenRepository.save(kaizen);
        Kaizen kaizen2 = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);
        kaizenRepository.save(kaizen2);
        Kaizen kaizen3 = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);
        kaizenRepository.save(kaizen3);


        kaizen.setUser(user);
        kaizen2.setUser(user);
        kaizen3.setUser(user);
        user.getKaizen().add(kaizen);
        user.getKaizen().add(kaizen2);
        user.getKaizen().add(kaizen3);

        userRepository.save(user);
        kaizenRepository.save(kaizen);
        kaizenRepository.save(kaizen2);
        kaizenRepository.save(kaizen3);

        //When
        List<Kaizen> kaizenListFromDb = kaizenDbService.getKaizenByUser(user.getUserId());

        //Then
        assertEquals(3, kaizenListFromDb.size());
        assertEquals(user.getUserId(),
                kaizenListFromDb.get(0).getUser().getUserId());
        assertEquals(user.getUserId(),
                kaizenListFromDb.get(1).getUser().getUserId());
        assertEquals(user.getUserId(),
                kaizenListFromDb.get(2).getUser().getUserId());

        //Clean up
        userRepository.deleteById(user.getUserId());
        userRepository.deleteById(user1.getUserId());
        kaizenRepository.deleteById(kaizen.getKaizenId());
        kaizenRepository.deleteById(kaizen2.getKaizenId());
        kaizenRepository.deleteById(kaizen3.getKaizenId());
    }
}