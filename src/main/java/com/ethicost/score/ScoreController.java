package com.ethicost.score;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class ScoreController {
    @GetMapping("/score")
    @ResponseBody
    public ScoreResponse getScore() {
        Random rand = new Random();
        ScoreResponse score = new ScoreResponse();
        score.setValue(rand.nextInt(100));

        return score;
    }
}
