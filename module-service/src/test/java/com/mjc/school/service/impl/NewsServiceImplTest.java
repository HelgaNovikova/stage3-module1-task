package com.mjc.school.service.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;
import com.mjc.school.service.dto.PieceOfNewsCreateDto;
import com.mjc.school.service.dto.PieceOfNewsResponseDto;
import com.mjc.school.service.dto.PieceOfNewsUpdateDto;
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

    private Repository<PieceOfNewsModel> repository;
    private NewsServiceImpl service;
    private PieceOfNewsModel pieceOfNews;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(Repository.class);
        service = new NewsServiceImpl(repository, new NewsValidator());
        now = LocalDateTime.of(2023, 3, 5, 12, 0, 30);
        pieceOfNews = new PieceOfNewsModel(1L, "title", "content", now, now, new AuthorModel(1L, "name"));
    }

    @Test
    void getAllNewsDto() {
        //GIVEN
        Mockito.when(repository.readAll()).thenReturn(List.of(pieceOfNews));
        PieceOfNewsResponseDto expected = new PieceOfNewsResponseDto();
        expected.setContent(pieceOfNews.getContent());
        expected.setId(pieceOfNews.getId());
        expected.setAuthorId(pieceOfNews.getAuthor().getId());
        expected.setTitle(pieceOfNews.getTitle());
        String date = "2023-03-05T12:00:30.000";
        expected.setCreateDate(date);
        expected.setLastUpdateDate(date);
        //WHEN
        var response = service.readAllDto();
        //THEN
        Mockito.verify(repository).readAll();
        Assertions.assertEquals(List.of(expected), response);
    }

    @Test
    void getNewsByIdDto() {
           //GIVEN
        Mockito.when(repository.readById(1L)).thenReturn(pieceOfNews);
        PieceOfNewsResponseDto expected = new PieceOfNewsResponseDto();
        expected.setContent(pieceOfNews.getContent());
        expected.setId(pieceOfNews.getId());
        expected.setAuthorId(pieceOfNews.getAuthor().getId());
        expected.setTitle(pieceOfNews.getTitle());
        String date = "2023-03-05T12:00:30.000";
        expected.setCreateDate(date);
        expected.setLastUpdateDate(date);
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
        PieceOfNewsUpdateDto dto = new PieceOfNewsUpdateDto(1, "new Title", "new Content", 1);
        PieceOfNewsModel updatedNews = new PieceOfNewsModel(1L, "new Title", "new Content", now, now,
                author);
        Mockito.when(repository.update(any())).thenReturn(updatedNews);
        Mockito.when(repository.getAuthorById(anyLong())).thenReturn(author);
        Mockito.when(repository.readById(anyLong())).thenReturn(pieceOfNews);
        PieceOfNewsResponseDto expected = new PieceOfNewsResponseDto();
        expected.setContent(dto.getContent());
        expected.setId(dto.getId());
        expected.setAuthorId(dto.getAuthorId());
        expected.setTitle(dto.getTitle());
        String date = "2023-03-05T12:00:30.000";
        expected.setCreateDate(date);
        expected.setLastUpdateDate(date);
        //WHEN
        var response = service.updatePieceOfNewsByIdDto(dto);
        //THEN
        Mockito.verify(repository).update(any());
        Assertions.assertEquals(expected, response);
    }

    @Test
    void createPieceOfNewsDto() {
        //GIVEN
        AuthorModel author = new AuthorModel(1, "name");
        PieceOfNewsCreateDto dto = new PieceOfNewsCreateDto(1, "new Title", "new Content");
        PieceOfNewsModel createdNews = new PieceOfNewsModel(1L, "new Title", "new Content", now, now,
                author);
        Mockito.when(repository.create(any())).thenReturn(createdNews);
        Mockito.when(repository.getAuthorById(anyLong())).thenReturn(author);
      //  Mockito.when(repository.getPieceOfNewsById(anyLong())).thenReturn(pieceOfNews);
        PieceOfNewsResponseDto expected = new PieceOfNewsResponseDto();
        expected.setContent(dto.getContent());
        expected.setAuthorId(dto.getAuthorId());
        expected.setTitle(dto.getTitle());
        expected.setId(1L);
        String date = "2023-03-05T12:00:30.000";
        expected.setCreateDate(date);
        expected.setLastUpdateDate(date);
        //WHEN
        var response = service.createPieceOfNewsDto(dto);
        //THEN
        Mockito.verify(repository).create(any());
        Assertions.assertEquals(expected, response);
    }
}