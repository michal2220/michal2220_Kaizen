package com.kaizen;

import com.kaizen.domain.Kaizen;
import com.kaizen.domain.User;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.dbService.UserDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class connectionWithDbTest {

    @Autowired
    private KaizenDbService kaizenDbService;

    @Autowired
    private UserDbService userDbService;


    @Test
    public void creatingKaizenTest(){
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
    public void creatingUserTest(){
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
}
