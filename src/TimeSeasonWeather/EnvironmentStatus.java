package TimeSeasonWeather;

import java.util.Random;

import Items.Seeds;
import main.GamePanel;

public class EnvironmentStatus {
    public enum Season {
        Spring, Summer, Fall, Winter
    }
    public enum Weather {
        Rainy, Sunny
    }
    public Season season;
    public Weather weather;
    public Time time;
    public int day;
    GamePanel gp;

    public EnvironmentStatus(Time time, GamePanel gp) {
        this.gp = gp;
        this.time = time;
        this.day = 1;
        this.weather = Weather.Sunny;
        this.season = Season.Spring; // Default season
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
                case Spring:
                    season = Season.Summer;
                    break;
                case Summer:
                    season = Season.Fall;
                    break;
                case Fall:
                    season = Season.Winter;
                    break;
                case Winter:
                    season = Season.Spring;
                    break;
            }
            day = 1; // Reset day to 1 for the new season
        }
    }

    public void nextDay() {
        if(time.getHour() == 0 && time.getMinute() == 0) {
            randomizeWeather();
            setDay(this.day + 1);
        if (day > 30) {
            day = 1; // Reset to 1 after 30 days
            nextSeason(); // Check for season change
        }
        changeDay();
        }
    }

    public void changeDay(){
        if(weather == Weather.Rainy){
            for(int x = 0; x < gp.farmMap.size; x++){
                for(int y = 0; y < gp.farmMap.size; y++){
                    if(gp.farmMap.mapTileNum[x][y] == 66 || gp.farmMap.mapTileNum[x][y] == 67){
                        gp.farmMap.mapTileNum[x][y] = 67; // pastikan basah
                        gp.farmMap.wetDaysLeft[x][y] = 2; // basah 2 hari
                    }
                }
            }
        }

        if(season == Season.Winter){
            for(int x = 0; x < gp.farmMap.size; x++){
                for(int y = 0; y < gp.farmMap.size; y++){
                    if(gp.farmMap.mapTileNum[x][y] == 66 || gp.farmMap.mapTileNum[x][y] == 67){
                        gp.farmMap.mapTileNum[x][y] = 67; // pastikan basah
                        gp.farmMap.wetDaysLeft[x][y] = 0; // basah 2 hari
                    }
                }
            }
        }

        // Di EnvironmentStatus.nextDay(), setelah update hujan
        for(int x = 0; x < gp.farmMap.size; x++){
            for(int y = 0; y < gp.farmMap.size; y++){
                if(gp.farmMap.mapTileNum[x][y] == 67){
                    gp.farmMap.wetDaysLeft[x][y]--;
                    if(gp.farmMap.wetDaysLeft[x][y] <= 0){
                        gp.farmMap.wetDaysLeft[x][y] = 0;
                        gp.farmMap.mapTileNum[x][y] = 66; // keringkan
                    }
                }
            }
        }

        for (Seeds seed : gp.player.inventory.plantedSeeds) {
            seed.incrementDayElapsed(); 
            if (seed.isHarvestable()) {
                int x = seed.posX;
                int y = seed.posY;
                gp.farmMap.mapTileNum[x][y] = 68; // ubah tile jadi siap panen
            }
        }
    }

    public void bangun(){
        if(time.getHour()<6){
            time.setHour(6);
            time.setMinute(0);
        }
        else if(time.getHour()<24){
            time.setHour(6);
            time.setMinute(0);
            randomizeWeather();
            day++;
            changeDay();
        }
    }

    public void marry(){
        if(time.getHour()<22){
            time.setHour(22);
            time.setMinute(0);
        }
        else{
            time.setHour(22);
            time.setMinute(0);
            randomizeWeather();
            day++;
        }
    }

    public void randomizeWeather(){
        if(season != Season.Winter){
            Random rand = new Random();
            int randomNumber = rand.nextInt(100000)%2;
            switch (randomNumber) {
                case 0:
                    setWeather(Weather.Rainy);
                    break;
                default:
                    setWeather(Weather.Sunny);
                    break;
            }
        }
    }
}