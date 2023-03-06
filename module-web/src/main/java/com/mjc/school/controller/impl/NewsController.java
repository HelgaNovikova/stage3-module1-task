package com.mjc.school.controller.impl;

import com.mjc.school.repository.model.PieceOfNewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.PieceOfNewsCreateDto;
import com.mjc.school.service.dto.PieceOfNewsResponseDto;
import com.mjc.school.service.dto.PieceOfNewsUpdateDto;

import java.util.List;

public class NewsController {
    private final NewsService<PieceOfNewsModel, PieceOfNewsResponseDto> newsService;

    public NewsController(NewsService<PieceOfNewsModel, PieceOfNewsResponseDto> newsService) {
        this.newsService = newsService;
    }

    public List<PieceOfNewsResponseDto> readAll() {
        return this.newsService.readAllDto();
    }

    public PieceOfNewsResponseDto readById(Long newsId) {
        return (PieceOfNewsResponseDto) this.newsService.readByIdDto(newsId);
    }

    public PieceOfNewsResponseDto create(PieceOfNewsCreateDto dtoRequest) {
        return (PieceOfNewsResponseDto) this.newsService.createPieceOfNewsDto(dtoRequest);
    }

    public PieceOfNewsResponseDto update(PieceOfNewsUpdateDto dtoRequest) {
        return (PieceOfNewsResponseDto) this.newsService.updatePieceOfNewsByIdDto(dtoRequest);
    }

    public Boolean deleteById(Long newsId) {
        return this.newsService.deleteNewsByIdDto(newsId);
    }
}
