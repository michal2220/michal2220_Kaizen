package com.kaizen;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.Reward;
import com.kaizen.domain.User;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.RewardDbService;
import com.kaizen.service.dbService.UserDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class connectionWithDbTest {

    @Autowired
    private KaizenDbService kaizenDbService;

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private RewardDbService rewardDbService;


    @Test
    public void creatingKaizenTest() {
        //Given
        Kaizen kaizen = new Kaizen();
        kaizen.setFillingDate(LocalDate.now());
        kaizen.setCompleted(false);
        kaizen.setProblem("Problem with lack of storage space on enrober");
        kaizen.setSolution("Create storage space by welding shelf to machine");
        kaizen.setRewarded(false);

        //When
        kaizenDbService.save(kaizen);

        //Then
        assertTrue(kaizenDbService.existsById(kaizen.getKaizenId()));

        //CleanUp
        kaizenDbService.deleteById(kaizen.getKaizenId());
        assertFalse(kaizenDbService.existsById(kaizen.getKaizenId()));
    }


    @Test
    public void creatingUserTest() {
        //Given
        User user = new User();
        user.setName("Krystyna");
        user.setLastname("Czubowna");
        user.setBirgade(4);

        //When
        userDbService.save(user);

        //Then
        assertTrue(userDbService.existsById(user.getUserId()));

        //CleanUp
        userDbService.deleteById(user.getUserId());
        assertFalse(userDbService.existsById(user.getUserId()));
    }

    @Test
    public void creatingRewardTest() {
        //Given
        Reward reward = new Reward();
        reward.setName("Shopping coupon");
        reward.setPrice(100);

        //When
        rewardDbService.save(reward);

        //Then
        assertTrue(rewardDbService.existsById(reward.getRewardId()));

        //CleanUp
        rewardDbService.deleteById(reward.getRewardId());
        assertFalse(rewardDbService.existsById(reward.getRewardId()));
    }


    @Test
    public void relationKaidenUserTest() {
        //Given
        User user = new User();
        user.setName("Krystyna");
        user.setLastname("Czubowna");
        user.setBirgade(4);

        userDbService.save(user);

        Kaizen kaizen = new Kaizen();
        kaizen.setFillingDate(LocalDate.now());
        kaizen.setCompleted(false);
        kaizen.setProblem("Problem with lack of storage space on enrober");
        kaizen.setSolution("Create storage space by welding shelf to machine");
        kaizen.setRewarded(false);

        kaizenDbService.save(kaizen);

        //When
        user.getKaizen().add(kaizen);
        kaizen.setUser(user);

        kaizenDbService.save(kaizen);
        userDbService.save(user);

        //Then
        assertNotNull(kaizen.getUser());
        assertNotNull(user.getKaizen());

        //CleanUp
        kaizenDbService.deleteById(kaizen.getKaizenId());
        userDbService.deleteById(user.getUserId());
    }

    @Test
    public void relationRewardKaizenTest() {
        //Given
        Kaizen kaizen = new Kaizen();
        kaizen.setFillingDate(LocalDate.now());
        kaizen.setCompleted(false);
        kaizen.setProblem("Problem with lack of storage space on enrober");
        kaizen.setSolution("Create storage space by welding shelf to machine");
        kaizen.setRewarded(false);

        kaizenDbService.save(kaizen);

        Reward reward = new Reward();
        reward.setName("Shopping coupon");
        reward.setPrice(100);

        rewardDbService.save(reward);

        //When
        reward.setKaizen(kaizen);
        kaizen.setReward(reward);

        //Then
        assertNotNull(kaizen.getReward());
        assertNotNull(reward.getKaizen());

        //CleanUp
        kaizenDbService.deleteById(kaizen.getKaizenId());
        rewardDbService.deleteById(reward.getRewardId());
        assertFalse(kaizenDbService.existsById(kaizen.getKaizenId()));
        assertFalse(rewardDbService.existsById(reward.getRewardId()));
    }
}
