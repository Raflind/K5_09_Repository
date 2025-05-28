package TimeSeasonWeather;

import java.util.Timer;
import java.util.TimerTask;

public class EnvironmentStatus {
    public enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }
    public enum Weather {
        RAINY, SUNNY
    }
    public Season season;
    public Weather weather;
    public Time time;
    public int day;

    public EnvironmentStatus(Time time) {
        this.time = time;
        this.day = 1;
        this.weather = Weather.RAINY;
        this.season = Season.SPRING; // Default season
    }

    public Weather getWeather() {
        return weather;
    }
    public Time getTime() {
        return time;
    }
    public int getDay() {
        return day;
    }
    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void displayEnvironmentStatus() {
        System.out.println("Season: " + season);
        System.out.println("Weather: " + weather);
        System.out.print("Time: ");
        time.displayTime();
        System.out.println("Day: " + day);
    }

    public void nextSeason(){
        if(day >= 30) {
            switch (season) {
                case SPRING:
                    season = Season.SUMMER;
                    break;
                case SUMMER:
                    season = Season.AUTUMN;
                    break;
                case AUTUMN:
                    season = Season.WINTER;
                    break;
                case WINTER:
                    season = Season.SPRING;
                    break;
            }
            day = 1; // Reset day to 1 for the new season
        }
    }

    public void nextDay() {
        if(time.getHour() == 0 && time.getMinute() == 0) {
            setDay(this.day + 1);
        if (day > 30) {
            day = 1; // Reset to 1 after 30 days
            nextSeason(); // Check for season change
        }
    }
    }
}