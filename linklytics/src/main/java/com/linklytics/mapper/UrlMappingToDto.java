package com.linklytics.mapper;

import com.linklytics.DTOS.UrlMappingDto;
import com.linklytics.models.UrlMapping;

public class UrlMappingToDto {

    public static UrlMappingDto getUrlMappToDto(UrlMapping urlMapping){
        UrlMappingDto urlMappingDto = new UrlMappingDto();
        urlMappingDto.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDto.setShortUrl(urlMapping.getShortUrl());
        urlMappingDto.setClickCount(urlMapping.getClickCount());
        urlMappingDto.setId(urlMapping.getId());
        urlMappingDto.setCreatedDate(urlMapping.getCreatedDate());
        return urlMappingDto;
    }

}
