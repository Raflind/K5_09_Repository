package TimeSeasonWeather;

public class EnvironmentStatus {
    private Season season;
    private Weather weather;
    private Time time;
    private int day;

    public EnvironmentStatus(Season season, Weather weather, Time time, int day) {
        this.time = time;
        this.day = day;
        this.season = season;
        this.weather = weather;
    }

    public Season getSeason() {
        return season;
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
    public void setSeason(Season season) {
        this.season = season;
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
}