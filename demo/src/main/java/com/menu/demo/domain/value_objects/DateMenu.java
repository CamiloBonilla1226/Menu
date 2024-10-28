package com.menu.demo.domain.value_objects;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

import com.menu.demo.domain.Menu;
import com.menu.demo.infrastructure.output.exceptionHandler.ownException.ObjectNullException;



@Getter
@Setter
public class DateMenu {
    private String date;
    private Menu objMenu; // no se

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