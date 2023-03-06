package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.PieceOfNews;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsMapper;
import com.mjc.school.service.dto.PieceOfNewsCreateDto;
import com.mjc.school.service.dto.PieceOfNewsResponseDto;
import com.mjc.school.service.dto.PieceOfNewsUpdateDto;
import com.mjc.school.service.exception.AuthorNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsServiceImpl implements NewsService {
    private Repository repo;

    public NewsServiceImpl(Repository repo) {
        this.repo = repo;
    }

    @Override
    public List<PieceOfNewsResponseDto> getAllNewsDto() {
        Map<Long, PieceOfNews> allNews = repo.readAll();
        List<PieceOfNewsResponseDto> newsDto = new ArrayList<>();
        for (PieceOfNews item : allNews.values()) {
            newsDto.add(NewsMapper.INSTANCE.newsToNewsResponseDto(item));
        }
        return newsDto;
    }

    @Override
    public PieceOfNewsResponseDto getNewsByIdDto(int id) {
        PieceOfNews news = repo.readById(id);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(news);
    }

    @Override
    public boolean deleteNewsByIdDto(long id) {
        return repo.deletePieceOfNewsById(id);
    }

    @Override
    public PieceOfNewsResponseDto updatePieceOfNewsByIdDto(PieceOfNewsUpdateDto dto) {
        Author author = getAuthorById(dto.getAuthorId());
        LocalDateTime createDate = repo.readById(dto.getId()).getCreateDate();
        PieceOfNews news = NewsMapper.INSTANCE.updateNewsDtoToNews(dto, author, createDate);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(repo.update(news));
    }

    private Author getAuthorById(long id) {
        Author author = repo.getAuthorById(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author Id does not exist. Author Id is: " + id);
        }
        return author;
    }

    @Override
    public PieceOfNewsResponseDto createPieceOfNewsDto(PieceOfNewsCreateDto dto) {
        Author author = getAuthorById(dto.getAuthorId());
        PieceOfNews news = NewsMapper.INSTANCE.createNewsDtoToNews(dto, author);
        return NewsMapper.INSTANCE.newsToNewsResponseDto(repo.create(news));
    }
}
