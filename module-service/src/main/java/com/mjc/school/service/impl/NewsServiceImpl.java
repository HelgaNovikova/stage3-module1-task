package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.dto.NewsMapper;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.dto.NewsUpdateDto;
import com.mjc.school.service.exception.AuthorNotFoundException;
import com.mjc.school.service.utils.NewsValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final Repository<NewsModel> newsRepository;
    private final NewsValidator newsValidator;

    public NewsServiceImpl(Repository<NewsModel> repository, NewsValidator newsValidator) {
        this.newsRepository = repository;
        this.newsValidator = newsValidator;
    }

    @Override
    public List<NewsResponseDto> readAllDto() {
        var allNews = newsRepository.readAll();
        List<NewsResponseDto> newsDto = new ArrayList<>();
        for (NewsModel item : allNews) {
            newsDto.add(NewsMapper.INSTANCE.newsToNewsResponseDto(item));
        }
        return newsDto;
    }

    @Override
    public NewsResponseDto readByIdDto(Long id) {
        NewsModel news = newsRepository.readById(id);
        NewsValidator.validateNewsPresence(id, news);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(news);
    }

    @Override
    public Boolean deleteNewsByIdDto(Long id) {
        NewsModel newsModel = newsRepository.readById(id);
        NewsValidator.validateNewsPresence(id, newsModel);
        return newsRepository.deletePieceOfNewsById(id);
    }

    @Override
    public NewsResponseDto updateNewsByIdDto(NewsUpdateDto dto) {
        AuthorModel author = getAuthorById(dto.getAuthorId());
        NewsModel newsModel = newsRepository.readById(dto.getId());
        NewsValidator.validateNewsPresence(dto.getId(), newsModel);
        NewsValidator.validateContent(dto.getContent());
        NewsValidator.validateTitle(dto.getTitle());
        LocalDateTime createDate = newsModel.getCreateDate();
        NewsModel news = NewsMapper.INSTANCE.updateNewsDtoToNews(dto, author, createDate);
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
    public NewsResponseDto createNewsDto(NewsCreateDto dto) {
        AuthorModel author = getAuthorById(dto.getAuthorId());
        NewsModel news = NewsMapper.INSTANCE.createNewsDtoToNews(dto, author);
        NewsValidator.validateContent(news.getContent());
        NewsValidator.validateTitle(news.getTitle());
        return NewsMapper.INSTANCE.newsToNewsResponseDto(newsRepository.create(news));
    }
}
