package com.menu.demo.domain.value_objects;

import java.time.LocalDate;
import lombok.Getter;
import java.time.format.DateTimeFormatter;

import  com.menu.demo.infrastucture.exceptionHandler.ownException.ObjectNullException;


@Getter

public class DateMenu {
    private String date;

    public DateMenu() {
        createMenuDate();
    }
    private void createMenuDate() {
        LocalDate myDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = myDate.format(dateFormatter);
        if (this.date == null) {
            throw new ObjectNullException("Date is null...");
        }
    }
}