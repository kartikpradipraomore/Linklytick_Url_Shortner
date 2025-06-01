package com.linklytics.DTOS;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlMappingDto {

    private Long id;
    private String originalUrl;
    private String shortUrl;
    private Integer clickCount = 0;
    private LocalDateTime createdDate;

    private String username;

}
