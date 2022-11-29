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

    public List<Holiday> getAllHolidays() {
        Iterable<Holiday> all = holidayRepository.findAll();
        List<Holiday> holidays = new ArrayList<>();
        all.forEach(holidays::add);
        return holidays;
    }

    public List<Holiday> getHolidaysByType(Holiday.Type holidayType) {
        return holidayRepository.findByType(holidayType);
    }
}
