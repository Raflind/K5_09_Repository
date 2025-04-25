package time;
public class Time{
    private int hour;
    private int minute;

    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }
    public int getHour(){
        return this.hour;
    }
    public int getMinute(){
        return this.minute;
    }
    public void setHour(int hour){
        if(0 <= hour && hour < 24){
            this.hour = hour;
        }
    }
    public void setMinute(int minute){
        if(0 <= minute && minute < 60){
            this.minute = minute;
        }
        else if(minute > 60){
            this.hour = minute / 60;
            this.minute = minute % 60;
        }
    }
    public void displayTime(){
        System.out.println(hour + ":" + minute);
    }
}