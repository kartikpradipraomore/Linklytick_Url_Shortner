package com.linklytics.service;


import com.linklytics.DTOS.ClickEventDto;
import com.linklytics.DTOS.UrlMappingDto;
import com.linklytics.models.UrlMapping;
import com.linklytics.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public interface UrlMappingService {


    UrlMappingDto createShorterUrl(String origUrl, User user);

    List<UrlMappingDto> getUrlsByUser(User user);

    List<ClickEventDto> getClickEventByDate(String shortUrl, LocalDateTime start, LocalDateTime end);

    Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end);

    UrlMapping getOrignalUrl(String shortUrl);
}
