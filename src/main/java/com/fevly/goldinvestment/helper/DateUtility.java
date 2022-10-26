package com.fevly.goldinvestment.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtility {
    public String getFormattedDate (String format){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        DateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(cal.getTime());
        return formattedDate;
    }
}
