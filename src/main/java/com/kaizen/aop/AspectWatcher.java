package com.kaizen.aop;

import com.kaizen.domain.AuditLog;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.domain.dto.RewardDto;
import com.kaizen.domain.dto.UserDto;
import com.kaizen.service.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AspectWatcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);
    private final AuditLogRepository auditLogRepository;

    @After("execution(* com.kaizen.controller.UserController.createUser(..)) && args(userDto)")
    public void logSavingUser(UserDto userDto) {
        String name = userDto.getName();
        String lastname = userDto.getLastname();
        LOGGER.info("LOGGING FROM ASPECT creation " + name + " " + lastname);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Adding new user " + name + " " + lastname));
    }

    @After("execution(* com.kaizen.controller.UserController.updateUser(..)) && args(userDto)")
    public void logUpdatingUser(UserDto userDto) {
        String name = userDto.getName();
        String lastname = userDto.getLastname();
        LOGGER.info("LOGGING FROM ASPECT updating user " + name + " " + lastname);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Updating user " + name + " " + lastname));
    }

    @After("execution(* com.kaizen.controller.UserController.deleteUser(..)) && args(userId)")
    public void logDeletingUser(int userId) {
        LOGGER.info("LOGGING FROM ASPECT deleting user with id: " + userId);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Deleting user with name: " + userId));
    }

    @After("execution(* com.kaizen.controller.KaizenController.createKaizen(..)) && args(kaizenDto)")
    public void logCreatingKaizen(KaizenDto kaizenDto) {
        String problem = kaizenDto.getProblem();
        String solution = kaizenDto.getSolution();
        LOGGER.info("LOGGING FROM ASPECT creating kaizen with problem: " + problem + ", solution: " + solution);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Creating kaizen with problem: " + problem + ", solution: " + solution));
    }

    @After("execution(* com.kaizen.controller.KaizenController.updateKaizen(..)) && args(kaizenDto)")
    public void logUpdatingKaizen(KaizenDto kaizenDto) {
        int kaizenId = kaizenDto.getKaizenId();
        String problem = kaizenDto.getProblem();
        String solution = kaizenDto.getSolution();
        LOGGER.info("LOGGING FROM ASPECT updating kaizen with id: " + kaizenId + ", problem: " + problem + ", solution: " + solution);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Updating kaizen with id: " + kaizenId + ", problem: " + problem + ", solution: " + solution));
    }

    @After("execution(* com.kaizen.controller.KaizenController.markAsCompleted(..)) && args(kaizenId, completionDate)")
    public void logCompletingKaizen(int kaizenId, LocalDate completionDate) {
        LOGGER.info("LOGGING FROM ASPECT completing kaizen with id: " + kaizenId);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Completing kaizen with id: " + kaizenId + " on date: " + completionDate));
    }

    @After("execution(* com.kaizen.controller.KaizenController.deleteKaizen(..)) && args(kaizenId)")
    public void logDeletingKaizen(int kaizenId) {
        LOGGER.info("LOGGING FROM ASPECT deleting kaizen with id: " + kaizenId);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Deleting kaizen with id: " + kaizenId));
    }

    @After("execution(* com.kaizen.controller.RewardController.createReward(..)) && args(rewardDto)")
    public void logCreatingReward(RewardDto rewardDto) {
        String rewardName = rewardDto.getName();
        LOGGER.info("LOGGING FROM ASPECT creating reward: " + rewardName);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Creating reward: " + rewardName));
    }

    @After("execution(* com.kaizen.controller.RewardController.updateReward(..)) && args(rewardDto)")
    public void logUpdatingReward(RewardDto rewardDto) {
        int rewardId = rewardDto.getRewardId();
        String rewardName = rewardDto.getName();
        LOGGER.info("LOGGING FROM ASPECT updating reward with id: " + rewardId + ", name: " + rewardName);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Updating reward with id: " + rewardId + ", name: " + rewardName));
    }

    @After("execution(* com.kaizen.controller.RewardController.deleteReward(..)) && args(rewardId)")
    public void logDeletingReward(int rewardId) {
        LOGGER.info("LOGGING FROM ASPECT deleting reward with name: " + rewardId);
        auditLogRepository.save(new AuditLog(
                LocalDate.now(),
                LocalTime.now(),
                "Deleting reward with name: " + rewardId));
    }
}
