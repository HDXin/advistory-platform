package top.atstudy.advistory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author huangdexin @ harley
 * @email huangdexin@kuaicto.com
 * @date 2019/5/29 15:48
 */
public class IDUtils {

    public static void main(String[] args) throws ParseException {

        write(1500);
        //13017499820
        //13018145889


        //f8bde378b055447a9dd810c19ec4e2d6
        //bcd670a1439e43369c726068c7be6832
        //454b1fe4dee44d63b718b447eff40382




    }



    private static void write(Integer num) throws ParseException {
        String begin = "2018-12-30 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long beginTime = sdf.parse(begin).getTime();
        for(int i=1; i<=num; i++){
            long currentTime = System.currentTimeMillis();
            System.out.print((currentTime - beginTime)/10 + " ");

            if(i%20 == 0)
                System.out.println();
        }


    }


}
