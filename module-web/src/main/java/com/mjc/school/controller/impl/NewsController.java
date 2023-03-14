package com.mjc.school.controller.impl;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.dto.NewsUpdateDto;

import java.util.List;

public class NewsController {
    private final NewsService<NewsModel, NewsResponseDto> newsService;

    public NewsController(NewsService<NewsModel, NewsResponseDto> newsService) {
        this.newsService = newsService;
    }

    public List<NewsResponseDto> readAll() {
        return this.newsService.readAllDto();
    }

    public NewsResponseDto readById(Long newsId) {
        return this.newsService.readByIdDto(newsId);
    }

    public NewsResponseDto create(NewsCreateDto dtoRequest) {
        return this.newsService.createNewsDto(dtoRequest);
    }

    public NewsResponseDto update(NewsUpdateDto dtoRequest) {
        return this.newsService.updateNewsByIdDto(dtoRequest);
    }

    public Boolean deleteById(Long newsId) {
        return this.newsService.deleteNewsByIdDto(newsId);
    }
}
