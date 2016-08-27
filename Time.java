import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jellyBananas on 2016/8/27.
 */
public class Time {
    public String getTime(){
        String time = null;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        time = simpleDateFormat.format(date);
        return time;
    }
}
