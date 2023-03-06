package com.mjc.school.service.dto;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.PieceOfNews;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-05T17:01:41-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 17.0.3.1 (Oracle Corporation)"
)
public class NewsMapperImpl implements NewsMapper {

    private final DateTimeFormatter dateTimeFormatter_yyyy_MM_dd_T_HH_mm_ss_SSS_0756264385 = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSS" );

    @Override
    public PieceOfNewsResponseDto newsToNewsResponseDto(PieceOfNews pieceOfNews) {
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
    public PieceOfNews createNewsDtoToNews(PieceOfNewsCreateDto dto, Author author) {
        if ( dto == null && author == null ) {
            return null;
        }

        String title = null;
        String content = null;
        if ( dto != null ) {
            title = dto.getTitle();
            content = dto.getContent();
        }
        Author author1 = null;
        Long id = null;
        if ( author != null ) {
            author1 = author;
            id = author.getId();
        }

        LocalDateTime lastUpdateDate = null;
        LocalDateTime createDate = null;

        PieceOfNews pieceOfNews = new PieceOfNews( id, title, content, createDate, lastUpdateDate, author1 );

        return pieceOfNews;
    }

    @Override
    public PieceOfNews updateNewsDtoToNews(PieceOfNewsUpdateDto dto, Author author) {
        if ( dto == null && author == null ) {
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
        Author author1 = null;
        author1 = author;

        LocalDateTime lastUpdateDate = null;
        LocalDateTime createDate = null;

        PieceOfNews pieceOfNews = new PieceOfNews( id, title, content, createDate, lastUpdateDate, author1 );

        return pieceOfNews;
    }

    private long pieceOfNewsAuthorId(PieceOfNews pieceOfNews) {
        if ( pieceOfNews == null ) {
            return 0L;
        }
        Author author = pieceOfNews.getAuthor();
        if ( author == null ) {
            return 0L;
        }
        long id = author.getId();
        return id;
    }
}
