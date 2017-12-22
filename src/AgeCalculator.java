import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeCalculator {

    private static Age calculateAge(Date birthDate)
    {
        int years=0;
        int months=0;
        int days=0;
        int lapse=0;
        long seconds=0;
        Calendar birthDay =  Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        long currentTime= System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);


        years = now.get(Calendar.YEAR)-birthDay.get(Calendar.YEAR);

        for(int i =0;i<years;i++){
            if(now.getActualMaximum(Calendar.DAY_OF_YEAR)!=365)
                lapse++;
            now.add(Calendar.YEAR,-1);
        }


        int currMonth = now.get(Calendar.MONTH)+1;
        int birthMonth = birthDay.get(Calendar.MONTH)+1;
        months = currMonth-birthMonth;


        if(months<0){
            years--;
            months=12+currMonth-birthMonth;
            if (now.get(Calendar.DATE)<birthDay.get(Calendar.DATE))
                months--;
        }
        else if(now.get(Calendar.DATE)<birthDay.get(Calendar.DATE))
                months--;
        else if (months==0 && now.get(Calendar.DATE)<birthDay.get(Calendar.DATE)){
            years--;
            months=11;
        }
        if (now.get(Calendar.DATE)>birthDay.get(Calendar.DATE))
            days=now.get(Calendar.DATE)-birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE)<birthDay.get(Calendar.DATE)){
            int today =now.get(Calendar.DATE);
            now.add(Calendar.MONTH,-1);
            days=now.getActualMaximum(Calendar.DATE) - birthDay.get(Calendar.DATE)+today;
            //why not days=birthDay.get(Calendar.DATE)-now.get(Calendar.DATE)
        }else{
            days=0;
            if(months==12){
                years++;
                months=0;
            }
        }
        seconds=getTimeInSec(years,months,days,lapse);
        return new Age(years,months,days,lapse,seconds);
    }

    public static void main(String[]args) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = sdf.parse("29/11/1995");
        Age age = calculateAge(birthDate);
        System.out.println(age);
    }

    static long getTimeInSec(int years,int months, int days, int lapse){
        long seconds=0;
        years-=lapse;
        days+=(years*365)+(lapse*366);
        if(months==1)
            days+=31;
        else if (months==2)
            days+=31+28;
        else if (months==3)
            days+=31+28+31;
        else if(months==4)
            days+=31+28+31+30;
        else if (months==5)
            days+=31+28+31+30+31;
        else if (months==6)
            days+=31+28+31+30+31+30;
        else if(months==7)
            days+=31+28+31+30+31+30+31;
        else if(months==8)
            days+=31+28+31+30+31+30+31+31;
        else if(months==9)
            days+=31+28+31+30+31+30+31+31+30;
        else if(months==10)
            days+=31+28+31+30+31+30+31+31+30+31;
        else if(months==11)
            days+=31+28+31+30+31+30+31+31+30+31+30;
        seconds=days*24*60*60;
        return seconds;
    }


}

