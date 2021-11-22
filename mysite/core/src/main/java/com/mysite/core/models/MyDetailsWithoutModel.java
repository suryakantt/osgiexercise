package com.mysite.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class MyDetailsWithoutModel extends WCMUsePojo {

    private String firstname;
    private String lastname;
    private String birthdate;
    private  String gender;
    private String maritalstatus;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
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
    @Override
    public void activate() throws Exception {
        firstname = (String) getProperties().get("firstname");
        lastname = getProperties().get("lastname", "");
        gender = (String) getProperties().get("gender");
        maritalstatus = (String) getProperties().get("maritalstatus");
        birthdate = getProperties().get("birthdate","");

    }
    public String getFullname()
    {
        return firstname + " "+ lastname;
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
    public int getAge() throws ParseException {

        String strdate = getBirthdate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parseddate = simpleDateFormat.parse(strdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseddate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        LocalDate l1 = LocalDate.of(year, month, date);
        LocalDate now1 = LocalDate.now();
        int age = Period.between(l1, now1).getYears();
        return age;

    }
}
