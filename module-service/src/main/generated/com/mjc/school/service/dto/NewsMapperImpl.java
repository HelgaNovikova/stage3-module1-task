package com.mjc.school.service.dto;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-05T20:37:18-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385 = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSS" );

    @Override
    public PieceOfNewsResponseDto newsToNewsResponseDto(PieceOfNewsModel pieceOfNews) {
        if ( pieceOfNews == null ) {
            return null;
        }

        PieceOfNewsResponseDto pieceOfNewsResponseDto = new PieceOfNewsResponseDto();

        if ( pieceOfNews.getLastUpdateDate() != null ) {
            pieceOfNewsResponseDto.setLastUpdateDate( dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385.format( pieceOfNews.getLastUpdateDate() ) );
        }
        if ( pieceOfNews.getCreateDate() != null ) {
            pieceOfNewsResponseDto.setCreateDate( dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385.format( pieceOfNews.getCreateDate() ) );
        }
        pieceOfNewsResponseDto.setAuthorId( pieceOfNewsAuthorId( pieceOfNews ) );
        if ( pieceOfNews.getId() != null ) {
            pieceOfNewsResponseDto.setId( pieceOfNews.getId() );
        }
        pieceOfNewsResponseDto.setTitle( pieceOfNews.getTitle() );
        pieceOfNewsResponseDto.setContent( pieceOfNews.getContent() );

        return pieceOfNewsResponseDto;
    }

    @Override
    public PieceOfNewsModel createNewsDtoToNews(PieceOfNewsCreateDto dto, AuthorModel author) {
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

        PieceOfNewsModel pieceOfNews = new PieceOfNewsModel( id, title, content, createDate, lastUpdateDate, author1 );

        return pieceOfNews;
    }

    @Override
    public PieceOfNewsModel updateNewsDtoToNews(PieceOfNewsUpdateDto dto, AuthorModel author, LocalDateTime createDate) {
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

        PieceOfNewsModel pieceOfNews = new PieceOfNewsModel( id, title, content, createDate1, lastUpdateDate, author1 );

        return pieceOfNews;
    }

    private long pieceOfNewsAuthorId(PieceOfNewsModel pieceOfNews) {
        if ( pieceOfNews == null ) {
            return 0L;
        }
        AuthorModel author = pieceOfNews.getAuthor();
        if ( author == null ) {
            return 0L;
        }
        long id = author.getId();
        return id;
    }
}
