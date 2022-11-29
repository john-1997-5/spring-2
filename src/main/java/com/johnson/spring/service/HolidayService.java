package com.johnson.spring.service;

import com.johnson.spring.model.Holiday;
import com.johnson.spring.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    @Autowired
    HolidayRepository holidayRepository;

    public List<Holiday> getHolidays(String holidayType) {
        Iterable<Holiday> all = holidayRepository.findAll();
        List<Holiday> holidays = new ArrayList<>();
        all.forEach(holidays::add);
        if (holidayType.equals("ALL")) {
            return holidays;
        }

        return holidays.stream()
                .filter(holiday -> holiday.getType().toString().equals(holidayType))
                .collect(Collectors.toList());
    }
}
