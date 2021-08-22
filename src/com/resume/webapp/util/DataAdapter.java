package com.resume.webapp.util;

import java.time.LocalDate;

public class DataAdapter {
    public static LocalDate convertStringToLocalDate(String date) {
        if(date==null|| date.trim().length() == 0){
            return LocalDate.of(0, 1, 1);
        }
        String[] array = date.split("-");
        return LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
    }
}
