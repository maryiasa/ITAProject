package com.itacademy.enums;

public enum Days {
    MONDAY("Monday", 1), TUESDAY("Tuesday", 2), FRIDAY("Friday", 5);

    Days(String dayname, int dayNumber) {
        this.dayname = dayname;
        this.dayNumber = dayNumber;
    }

    private  String dayname;
    private  int dayNumber;

    public String getDayname() {
        return dayname;
    }

    public void setDayname(String dayname) {
        this.dayname = dayname;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }
}
