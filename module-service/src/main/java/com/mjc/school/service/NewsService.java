package com.mjc.school.service;

import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.dto.NewsUpdateDto;

import java.util.List;

public interface NewsService<T,D> {
    List<NewsResponseDto> readAllDto();
    NewsResponseDto readByIdDto(Long id);
    Boolean deleteNewsByIdDto(Long id);
    NewsResponseDto updateNewsByIdDto(NewsUpdateDto dto);

    NewsResponseDto createNewsDto(NewsCreateDto dto);

}
