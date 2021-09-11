package main.service;


import main.api.response.CalendarResponse;

public interface CalendarService {

    CalendarResponse getCalendar(String year);
}
