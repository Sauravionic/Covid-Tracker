package io.ionic.covidtracker.model;

public class DeathStats {

    private String state;
    private String country;
    private int totalDeathsToday;
    private int deathYesterDay;

    public int getDeathYesterDay() {
        return deathYesterDay;
    }

    public void setDeathYesterDay(int deathYesterDay) {
        this.deathYesterDay = deathYesterDay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalDeathsToday() {
        return totalDeathsToday;
    }

    public void setTotalDeathsToday(int totalDeathsToday) {
        this.totalDeathsToday = totalDeathsToday;
    }


    @Override
    public String toString() {
        return "DeathStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalDeathsToday=" + totalDeathsToday +
                ", deathYesterDay=" + deathYesterDay +
                '}';
    }
}
