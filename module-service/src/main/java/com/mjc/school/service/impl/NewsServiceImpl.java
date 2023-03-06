package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsMapper;
import com.mjc.school.service.dto.PieceOfNewsCreateDto;
import com.mjc.school.service.dto.PieceOfNewsResponseDto;
import com.mjc.school.service.dto.PieceOfNewsUpdateDto;
import com.mjc.school.service.exception.AuthorNotFoundException;
import com.mjc.school.service.utils.NewsValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private Repository newsRepository;
    private NewsValidator newsValidator;

    public NewsServiceImpl(Repository repo) {
        this.newsRepository = repo;
    }

    @Override
    public List<PieceOfNewsResponseDto> readAllDto() {
        var allNews = newsRepository.readAll();
        List<PieceOfNewsResponseDto> newsDto = new ArrayList<>();
        for (PieceOfNewsModel item : allNews) {
            newsDto.add(NewsMapper.INSTANCE.newsToNewsResponseDto(item));
        }
        return newsDto;
    }

    @Override
    public PieceOfNewsResponseDto readByIdDto(Long id) {
        PieceOfNewsModel news = newsRepository.readById(id);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(news);
    }

    @Override
    public Boolean deleteNewsByIdDto(Long id) {
        return newsRepository.deletePieceOfNewsById(id);
    }

    @Override
    public PieceOfNewsResponseDto updatePieceOfNewsByIdDto(PieceOfNewsUpdateDto dto) {
        AuthorModel author = getAuthorById(dto.getAuthorId());
        LocalDateTime createDate = newsRepository.readById(dto.getId()).getCreateDate();
        PieceOfNewsModel news = NewsMapper.INSTANCE.updateNewsDtoToNews(dto, author, createDate);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(newsRepository.update(news));
    }

    private AuthorModel getAuthorById(long id) {
        AuthorModel author = newsRepository.getAuthorById(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author Id does not exist. Author Id is: " + id);
        }
        return author;
    }

    @Override
    public PieceOfNewsResponseDto createPieceOfNewsDto(PieceOfNewsCreateDto dto) {
        AuthorModel author = getAuthorById(dto.getAuthorId());
        PieceOfNewsModel news = NewsMapper.INSTANCE.createNewsDtoToNews(dto, author);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(newsRepository.create(news));
    }
}
