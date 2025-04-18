package com.nhkhang.scoresystem.controller;

import com.nhkhang.scoresystem.model.Score;
import com.nhkhang.scoresystem.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService = new ScoreService();

    @GetMapping("/{regNum}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Score> find(@PathVariable Long regNum) {
        return ResponseEntity.ok(scoreService.findOneByRegistrationNumber(regNum));
    }

    @GetMapping("/top10GroupA")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Score>> findByTop10GroupA() {
        return ResponseEntity.ok(scoreService.findTop10GroupA());
    }

    @GetMapping("/statistics/{subject}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getStatisticsForSubject(@PathVariable String subject) {
        Map<String, Object> stats = scoreService.getStatsBySubject(subject);
        return ResponseEntity.ok(stats);
    }
}
