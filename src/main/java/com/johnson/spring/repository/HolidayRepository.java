package com.johnson.spring.repository;

import com.johnson.spring.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {
    List<Holiday> findByType(Holiday.Type type);
}