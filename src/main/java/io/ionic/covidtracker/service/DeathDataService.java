package io.ionic.covidtracker.service;

import io.ionic.covidtracker.model.DeathStats;
import io.ionic.covidtracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeathDataService {

    public static String DEATH_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

    private List<DeathStats> alldeathStats = new ArrayList<>();

    public List<DeathStats> getDeathStats() {
        return alldeathStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchDeathData() throws IOException, InterruptedException {
        List<DeathStats> newdeathStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(DEATH_DATA_URL)).
                build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            DeathStats deathStats = new DeathStats();
            if(!record.get("Province/State").equals("")) {
                deathStats.setState((record.get("Province/State")));
            }
            else {
                deathStats.setState("Unknown/Somewhere in " + record.get("Country/Region"));
            }
                deathStats.setCountry(record.get("Country/Region"));
                deathStats.setTotalDeathsToday(Integer.parseInt(record.get(record.size() - 1)));
                int totalCasesuptoToday = Integer.parseInt(record.get(record.size() - 1));
                int totalCasesuptoYesterday = Integer.parseInt(record.get(record.size() - 2));
                int deathYesterday = totalCasesuptoToday - totalCasesuptoYesterday;
                deathStats.setDeathYesterDay(Integer.parseInt(String.valueOf(deathYesterday)));

                newdeathStats.add(deathStats);

        }
        this.alldeathStats = newdeathStats;

    }
}
