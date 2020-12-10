package org.libreapps.thinkit.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AppUtils {

    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    public static String getUniqueId(){
        Random rand = new Random();
        long accumulator = 1 + rand.nextInt(9); // ensures that the 16th digit isn't 0
        for(int i = 0; i < 15; i++) {
            accumulator *= 10L;
            accumulator += rand.nextInt(10);
        }

        return String.valueOf(accumulator);
    }

}
