package com.kaizen.controller;

import com.kaizen.api.Translator;
import com.kaizen.controller.exception.KaizenNotFoundException;
import com.kaizen.controller.exception.RewardNotFoundException;
import com.kaizen.domain.Kaizen;
import com.kaizen.domain.dto.KaizenDto;
import com.kaizen.mapper.KaizenMapper;
import com.kaizen.scheduler.EmailScheduler;
import com.kaizen.service.dbService.KaizenDbService;
import com.kaizen.service.infoToKaizen.KaizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/kaizens")
@RequiredArgsConstructor
public class KaizenController {

    private final KaizenDbService kaizenDbService;
    private final KaizenMapper kaizenMapper;
    private final KaizenService kaizenService;
    private final Translator translator;



    @GetMapping(value = "/kaizenId/{kaizenId}")
    public ResponseEntity<KaizenDto> getRewardById(@PathVariable int kaizenId) throws KaizenNotFoundException {
        return ResponseEntity.ok(kaizenMapper.mapToKaizenDto(kaizenDbService.getKaizen(kaizenId)));
    }

    @GetMapping
    public ResponseEntity<List<KaizenDto>> getAllKaizens() {
        List<Kaizen> allKaizens = kaizenDbService.getAllKaizens();
        return ResponseEntity.ok(kaizenMapper.mapToKaizenDtoList(allKaizens));
    }

    @GetMapping(value = "/olderThen/{date}")
    public ResponseEntity<List<KaizenDto>> getKaizensOlderThen(@PathVariable LocalDate date) {
        List<Kaizen> kaizens = kaizenDbService.kaizensOlderThen(date);
        return ResponseEntity.ok(kaizenMapper.mapToKaizenDtoList(kaizens));
    }

    @GetMapping(value = "/creator")
    public ResponseEntity<List<KaizenDto>> getKaizensCreatedBy(@RequestParam("name") String name, @RequestParam("lastname") String lastname) {
        List<Kaizen> kaizens = kaizenDbService.getKaizensCreatedBy(name, lastname);
        return ResponseEntity.ok(kaizenMapper.mapToKaizenDtoList(kaizens));
    }

    @GetMapping(value = "/translate/{kaizenId}")
    public ResponseEntity<String> translateKaizenById(@PathVariable int kaizenId) throws KaizenNotFoundException {
        Kaizen kaizen = kaizenDbService.getKaizen(kaizenId);
        String problem = kaizen.getProblem();
        String translatedIdea = translator.doTranslate(problem);
        return ResponseEntity.ok(translatedIdea);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createKaizen(@RequestBody KaizenDto kaizenDto) throws KaizenNotFoundException, RewardNotFoundException {
        Kaizen kaizen = kaizenMapper.mapToKaizen(kaizenDto);
        kaizenService.addKaizen(kaizen);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<KaizenDto> updateKaizen(@RequestBody KaizenDto kaizenDto) throws RewardNotFoundException {
        Kaizen kaizen = kaizenMapper.mapToKaizen(kaizenDto);
        Kaizen savedKaizen = kaizenDbService.saveKaizen(kaizen);
        return ResponseEntity.ok(kaizenMapper.mapToKaizenDto(savedKaizen));
    }

    @PutMapping(value = "markAsCompleted/{kaizendId}")
    public ResponseEntity<KaizenDto> markAsCompleted(@PathVariable int kaizendId, @RequestParam("completionDate") LocalDate completionDate) throws KaizenNotFoundException {
        Kaizen kaizen = kaizenDbService.getKaizen(kaizendId);
        if (kaizen.isCompleted()) {
            kaizen.setCompleted(true);
            kaizen.setCompletionDate(completionDate);
        }
        Kaizen savedKaizen = kaizenDbService.saveKaizen(kaizen);
        return ResponseEntity.ok(kaizenMapper.mapToKaizenDto(savedKaizen));
    }

    @DeleteMapping(value = "{kaizenId}")
    public ResponseEntity<Void> deleteKaizen(@PathVariable int kaizenId) throws KaizenNotFoundException {
        kaizenDbService.deleteKaizenById(kaizenId);
        return ResponseEntity.ok().build();
    }
}
