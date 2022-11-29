package com.johnson.spring.service;

import com.johnson.spring.model.Holiday;
import com.johnson.spring.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {

    @Autowired
    HolidayRepository holidayRepository;

    public List<Holiday> getHolidays(String holidayType) {
        if (holidayType.equals("ALL")) {
            return holidayRepository.fetchAllHolidays();
        }
        return holidayRepository.fetchHolidaysByType(holidayType);
    }
}
