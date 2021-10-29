package com.mysite.core.models;

import org.apache.sling.scripting.sightly.pojo.Use;

import javax.script.Bindings;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WhatsDate implements Use {
    Date date;
    DateFormat dateFormat;
    String today;

    @Override
    public void init(Bindings bindings) {
        date = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        today = dateFormat.format(date);
    }

    public String getToday(){
        return today;
    }
}