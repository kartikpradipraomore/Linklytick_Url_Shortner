package com.linklytics.controllers;

import com.linklytics.DTOS.ClickEventDto;
import com.linklytics.DTOS.UrlMappingDto;
import com.linklytics.mapper.UrlMappingToDto;
import com.linklytics.models.User;
import com.linklytics.service.UrlMappingService;
import com.linklytics.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlMappingController {

    private UrlMappingService urlMappingService;
    private UserService userService;

    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDto> createShortUrl(@RequestBody Map<String,String> req, Principal principal){

        String origUrl = req.get("originalUrl");
        User user = userService.findByUserName(principal.getName());
        UrlMappingDto urlMappingDto = urlMappingService.createShorterUrl(origUrl,user);
        return  ResponseEntity.ok(urlMappingDto);

    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDto>> getUserUrls(Principal principal){
        User user = userService.findByUserName(principal.getName());
        List<UrlMappingDto> urls = urlMappingService.getUrlsByUser(user);
        return ResponseEntity.ok(urls);
    }

    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDto>> getUrlAnalytics(@PathVariable String shortUrl, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){


        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate,formatter);
        LocalDateTime end = LocalDateTime.parse(endDate,formatter);
        List<ClickEventDto> clickEventDtos = urlMappingService.getClickEventByDate(shortUrl, start,end);
        return ResponseEntity.ok(clickEventDtos);
    }


    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate,Long>> getUrlTotalClicks(Principal principal, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){

        User user = userService.findByUserName(principal.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);
        Map<LocalDate,Long> totalClicks = urlMappingService.getTotalClicksByUserAndDate(user, start,end);
        return ResponseEntity.ok(totalClicks);
    }

}






