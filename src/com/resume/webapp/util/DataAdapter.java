package com.resume.webapp.util;

import java.time.LocalDate;

public class DataAdapter {
    public static LocalDate convertStringToLocalDate(String date) {
        if(date==null){
            return null;
        }
        String[] array = date.split("-");
        return LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
    }
}
