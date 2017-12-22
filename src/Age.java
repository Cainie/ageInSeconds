public class Age {

    private int years;
    private int months;
    private int days;
    private int lapse;
    private long seconds;

    private Age()
    {

    }

    public Age (int years, int months, int days, int lapse,long seconds)
    {
        this.years=years;
        this.months=months;
        this.days=days;
        this.lapse=lapse;
        this.seconds=seconds;
    }

    public int getYears() {
        return this.years;
    }

    public int getMonths() {
        return this.months;
    }

    public int getDays() {
        return this.days;
    }

    public int getLapse() {return this.lapse;}

    public long getSeconds() {return this.seconds;}

    @Override
    public String toString() {
        return years +" Years " + months + " months "+days+" days"+ "\nIlość lat przestępnych "+lapse + "\nIlość sekund "+seconds ;
    }
    //no idea what toString does, is it different from sout?
}
