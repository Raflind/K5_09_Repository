package time;

public enum Season{
    Summer(1),
    Winter(1),
    Fall(1),
    Spring(1);

    private int day;

    Season(int day){
        this.day = day;
    }

    public int getDay(){
        return this.day;
    }

    public void setDay(int day){
        this.day = day;
    }
}