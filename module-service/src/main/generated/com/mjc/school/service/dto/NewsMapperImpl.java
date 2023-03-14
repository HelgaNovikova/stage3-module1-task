package com.mjc.school.service.dto;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-09T14:06:22-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385 = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSS" );

    @Override
    public NewsResponseDto newsToNewsResponseDto(NewsModel pieceOfNews) {
        if ( pieceOfNews == null ) {
            return null;
        }

        NewsResponseDto newsResponseDto = new NewsResponseDto();

        if ( pieceOfNews.getLastUpdateDate() != null ) {
            newsResponseDto.setLastUpdateDate( dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385.format( pieceOfNews.getLastUpdateDate() ) );
        }
        if ( pieceOfNews.getCreateDate() != null ) {
            newsResponseDto.setCreateDate( dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385.format( pieceOfNews.getCreateDate() ) );
        }
        newsResponseDto.setAuthorId( pieceOfNewsAuthorId( pieceOfNews ) );
        if ( pieceOfNews.getId() != null ) {
            newsResponseDto.setId( pieceOfNews.getId() );
        }
        newsResponseDto.setTitle( pieceOfNews.getTitle() );
        newsResponseDto.setContent( pieceOfNews.getContent() );

        return newsResponseDto;
    }

    @Override
    public NewsModel createNewsDtoToNews(NewsCreateDto dto, AuthorModel author) {
        if ( dto == null && author == null ) {
            return null;
        }

        String title = null;
        String content = null;
        if ( dto != null ) {
            title = dto.getTitle();
            content = dto.getContent();
        }
        AuthorModel author1 = null;
        author1 = author;

        LocalDateTime lastUpdateDate = null;
        LocalDateTime createDate = null;
        Long id = null;

        NewsModel newsModel = new NewsModel( id, title, content, createDate, lastUpdateDate, author1 );

        return newsModel;
    }

    @Override
    public NewsModel updateNewsDtoToNews(NewsUpdateDto dto, AuthorModel author, LocalDateTime createDate) {
        if ( dto == null && author == null && createDate == null ) {
            return null;
        }

        String title = null;
        String content = null;
        Long id = null;
        if ( dto != null ) {
            title = dto.getTitle();
            content = dto.getContent();
            id = dto.getId();
        }
        AuthorModel author1 = null;
        author1 = author;
        LocalDateTime createDate1 = null;
        createDate1 = createDate;

        LocalDateTime lastUpdateDate = null;

        NewsModel newsModel = new NewsModel( id, title, content, createDate1, lastUpdateDate, author1 );

        return newsModel;
    }

    private long pieceOfNewsAuthorId(NewsModel newsModel) {
        if ( newsModel == null ) {
            return 0L;
        }
        AuthorModel author = newsModel.getAuthor();
        if ( author == null ) {
            return 0L;
        }
        long id = author.getId();
        return id;
    }
}
