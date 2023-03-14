package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.dto.NewsResponseDto;
import com.mjc.school.service.dto.NewsUpdateDto;
import com.mjc.school.service.utils.NewsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

class NewsServiceImplTest {

    private Repository<NewsModel> repository;
    private NewsServiceImpl service;
    private NewsModel pieceOfNews;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(Repository.class);
        service = new NewsServiceImpl(repository, new NewsValidator());
        now = LocalDateTime.of(2023, 3, 5, 12, 0, 30);
        pieceOfNews = new NewsModel(1L, "title", "content", now, now, new AuthorModel(1L, "name"));
    }

    @Test
    void getAllNewsDto() {
        //GIVEN
        Mockito.when(repository.readAll()).thenReturn(List.of(pieceOfNews));
        NewsResponseDto expected = getExpectedNewsResponseDto(pieceOfNews.getContent(),
                pieceOfNews.getId(), pieceOfNews.getAuthor().getId(), pieceOfNews.getTitle());
        //WHEN
        var response = service.readAllDto();
        //THEN
        Mockito.verify(repository).readAll();
        Assertions.assertEquals(List.of(expected), response);
    }

    private NewsResponseDto getExpectedNewsResponseDto(String content, Long id, long authorId, String title) {
        NewsResponseDto expected = new NewsResponseDto();
        expected.setContent(content);
        expected.setId(id);
        expected.setAuthorId(authorId);
        expected.setTitle(title);
        String date = "2023-03-05T12:00:30.000";
        expected.setCreateDate(date);
        expected.setLastUpdateDate(date);
        return expected;
    }

    @Test
    void getNewsByIdDto() {
        //GIVEN
        Mockito.when(repository.readById(1L)).thenReturn(pieceOfNews);
        NewsResponseDto expected = getExpectedNewsResponseDto(pieceOfNews.getContent(),
                pieceOfNews.getId(), pieceOfNews.getAuthor().getId(), pieceOfNews.getTitle());
        //WHEN
        var response = service.readByIdDto(1L);
        //THEN
        Mockito.verify(repository).readById(1L);
        Assertions.assertEquals(expected, response);
    }

    @Test
    void deleteNewsByIdDto() {
        Mockito.when(repository.readById(1L)).thenReturn(pieceOfNews);
        Mockito.when(repository.deletePieceOfNewsById(1L)).thenReturn(true);
        var response = service.deleteNewsByIdDto(1L);
        Assertions.assertEquals(response, true);
        Mockito.verify(repository).deletePieceOfNewsById(anyLong());
    }

    @Test
    void updatePieceOfNewsByIdDto() {
        //GIVEN
        AuthorModel author = new AuthorModel(1, "name");
        NewsUpdateDto dto = new NewsUpdateDto(1, "new Title", "new Content", 1);
        NewsModel updatedNews = new NewsModel(1L, "new Title", "new Content", now, now,
                author);
        Mockito.when(repository.update(any())).thenReturn(updatedNews);
        Mockito.when(repository.getAuthorById(anyLong())).thenReturn(author);
        Mockito.when(repository.readById(anyLong())).thenReturn(pieceOfNews);
        NewsResponseDto expected = getExpectedNewsResponseDto(dto.getContent(),
                dto.getId(), dto.getAuthorId(), dto.getTitle());
        //WHEN
        var response = service.updateNewsByIdDto(dto);
        //THEN
        Mockito.verify(repository).update(any());
        Assertions.assertEquals(expected, response);
    }

    @Test
    void createPieceOfNewsDto() {
        //GIVEN
        AuthorModel author = new AuthorModel(1, "name");
        NewsCreateDto dto = new NewsCreateDto(1, "new Title", "new Content");
        NewsModel createdNews = new NewsModel(1L, "new Title", "new Content", now, now,
                author);
        Mockito.when(repository.create(any())).thenReturn(createdNews);
        Mockito.when(repository.getAuthorById(anyLong())).thenReturn(author);
        NewsResponseDto expected = getExpectedNewsResponseDto(dto.getContent(), 1L, dto.getAuthorId(), dto.getTitle());
        //WHEN
        var response = service.createNewsDto(dto);
        //THEN
        Mockito.verify(repository).create(any());
        Assertions.assertEquals(expected, response);
    }
}