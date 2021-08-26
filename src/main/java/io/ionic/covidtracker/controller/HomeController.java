package io.ionic.covidtracker.controller;

import io.ionic.covidtracker.model.LocationStats;
import io.ionic.covidtracker.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = covidDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalCases);

        return "home";
    }
}
