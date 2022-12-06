package com.johnson.spring.model.entities;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Clase aparte que contiene atributos que se repiten en varias
 * clases del modelo, así se evita redundancia.
 */
@Data
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}