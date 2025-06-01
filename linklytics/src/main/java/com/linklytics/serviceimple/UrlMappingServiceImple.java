package com.linklytics.serviceimple;

import com.linklytics.DTOS.ClickEventDto;
import com.linklytics.DTOS.UrlMappingDto;
import com.linklytics.mapper.UrlMappingToDto;
import com.linklytics.models.ClickEvent;
import com.linklytics.models.UrlMapping;
import com.linklytics.models.User;
import com.linklytics.repository.ClickEventRepository;
import com.linklytics.repository.UrlMappingRepository;
import com.linklytics.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UrlMappingServiceImple implements UrlMappingService {

    private UrlMappingRepository urlMappingRepository;
    private ClickEventRepository clickEventRepository;

    @Override
    public UrlMappingDto createShorterUrl(String origUrl, User user) {

        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setOriginalUrl(origUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping = urlMappingRepository.save(urlMapping);
        return UrlMappingToDto.getUrlMappToDto(savedUrlMapping);


    }

    @Override
    public List<UrlMappingDto> getUrlsByUser(User user) {
        return urlMappingRepository.findByUser(user).stream().map(
                UrlMappingToDto::getUrlMappToDto
        ).toList();
    }

    @Override
    public List<ClickEventDto> getClickEventByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping != null){
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping,start,end).stream()
                    .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(),Collectors.counting()))
                    .entrySet().stream()
                    .map(
                            entry -> {
                                ClickEventDto clickEventDto = new ClickEventDto();
                                clickEventDto.setClickDate(entry.getKey());
                                clickEventDto.setCount(Math.toIntExact(entry.getValue()));
                                return clickEventDto;
            }
                    ).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMapping =  urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents = clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMapping,start.atStartOfDay(),end.plusDays(1).atStartOfDay());
       return clickEvents.stream()
                .collect(Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()));
    }

    @Override
    public UrlMapping getOrignalUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);

        if(urlMapping != null){
            urlMapping.setClickCount(urlMapping.getClickCount()+1);
            urlMappingRepository.save(urlMapping);

            // Record Click Event
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setClickDate(LocalDateTime.now());
            clickEvent.setUrlMapping(urlMapping);
            clickEventRepository.save(clickEvent);
        }

        return urlMapping;
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }

        return shortUrl.toString();
    }



}
