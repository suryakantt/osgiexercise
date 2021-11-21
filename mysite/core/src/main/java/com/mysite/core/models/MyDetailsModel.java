package com.mysite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyDetailsModel {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Inject
    private String firstname;

    @Inject
    private String lname;

    @Inject
    private String birthdate;

    @Inject
    private  String gender;

    @Inject
    private String maritalstatus;

    public String getFirstname() {
        return firstname;
    }

    public String getLname() {
        return lname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public String getFullname()
    {
        return firstname + " "+ lname;
    }

    public int getAge() throws ParseException {

        //using Calendar Object
        String s = getBirthdate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = simpleDateFormat.parse(s);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        int age = Period.between(l1, now1).getYears();
        return age;

    }

    public String getHonorific()
    {
        String gender=getGender();
        String maritalStatus=getMaritalstatus();

        if(gender.equals("male"))
        {
            return "Mr";
        }
        else if(gender.equals("female") && maritalStatus.equals("single"))
        {
            return "Ms";
        }
        else
        {
            return "Mrs";
        }
    }
}













