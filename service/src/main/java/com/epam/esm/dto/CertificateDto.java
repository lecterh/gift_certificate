package com.epam.esm.dto;

import com.epam.esm.entity.Tag;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CertificateDto {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime create;
    private LocalDateTime update;
    private List<Tag> tags;

}
