package com.kaizen;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.service.repository.KaizenRepository;
import com.kaizen.service.repository.RewardRepository;
import com.kaizen.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class connectionWithDbTest {

    @Autowired
    private KaizenRepository kaizenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;


    @Test
    public void creatingKaizenTest() {
        //Given
        Kaizen kaizen = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);
        //When
        kaizenRepository.save(kaizen);

        //Then
        assertTrue(kaizenRepository.existsById(kaizen.getKaizenId()));

        //CleanUp
        kaizenRepository.deleteById(kaizen.getKaizenId());
        assertFalse(kaizenRepository.existsById(kaizen.getKaizenId()));
    }


    @Test
    public void creatingUserTest() {
        //Given
        User user = new User("Krystyna", "Czubowna", 4);

        //When
        userRepository.save(user);

        //Then
        assertTrue(userRepository.existsById(user.getUserId()));

        //CleanUp
        userRepository.deleteById(user.getUserId());
        assertFalse(userRepository.existsById(user.getUserId()));
    }

    @Test
    public void creatingRewardTest() {
        //Given
        Reward reward = new Reward("Shopping coupon", 100);

        //When
        rewardRepository.save(reward);

        //Then
        assertTrue(rewardRepository.existsById(reward.getRewardId()));

        //CleanUp
        rewardRepository.deleteById(reward.getRewardId());
        assertFalse(rewardRepository.existsById(reward.getRewardId()));
    }


    @Test
    public void relationKaidenUserTest() {
        //Given
        User user = new User("Krystyna", "Czubowna", 4);

        userRepository.save(user);

        Kaizen kaizen = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);

        kaizenRepository.save(kaizen);

        //When
        user.getKaizen().add(kaizen);
        kaizen.setUser(user);

        kaizenRepository.save(kaizen);
        userRepository.save(user);

        //Then
        assertNotNull(kaizen.getUser());
        assertNotNull(user.getKaizen());

        //CleanUp
        kaizenRepository.deleteById(kaizen.getKaizenId());
        userRepository.deleteById(user.getUserId());
    }

    @Test
    public void relationRewardKaizenTest() {
        //Given
        Kaizen kaizen = new Kaizen(LocalDate.now(), false,
                "Problem with lack of storage space on enrober",
                "Create storage space by welding shelf to machine",
                false);

        kaizenRepository.save(kaizen);

        Reward reward = new Reward("Shopping coupon", 100);

        rewardRepository.save(reward);

        //When
        reward.setKaizen(kaizen);
        kaizen.setReward(reward);

        //Then
        assertNotNull(kaizen.getReward());
        assertNotNull(reward.getKaizen());

        //CleanUp
        kaizenRepository.deleteById(kaizen.getKaizenId());
        rewardRepository.deleteById(reward.getRewardId());
        assertFalse(kaizenRepository.existsById(kaizen.getKaizenId()));
        assertFalse(rewardRepository.existsById(reward.getRewardId()));
    }

/*    @Test
    public void cleanAllTablesTest() {
        //Given & When
        kaizenRepository.deleteAll();
        userRepository.deleteAll();
        rewardRepository.deleteAll();

        //Then
        assertEquals(0, kaizenRepository.findAll().size());
        assertEquals(0, userRepository.findAll().size());
        assertEquals(0, rewardRepository.findAll().size());
    }*/
}
