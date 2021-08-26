package io.ionic.covidtracker.controller;

import io.ionic.covidtracker.model.DeathStats;
import io.ionic.covidtracker.service.DeathDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeathController {

    @Autowired
    DeathDataService deathDataService;

    @GetMapping("/death")
    public String death(Model model) {
        List<DeathStats> alldeathStats = deathDataService.getDeathStats();
        int totalDeaths = alldeathStats.stream().mapToInt(stat -> stat.getTotalDeathsToday()).sum();
        model.addAttribute("allDeathStats",alldeathStats);
        model.addAttribute("totalDeaths",totalDeaths);
        return "death";
    }
}
