package com.mjc.school.service.dto;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.PieceOfNews;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Mapping(target = "lastUpdateDate",
            dateFormat = ISO_FORMAT)
    @Mapping(target = "createDate",
            dateFormat = ISO_FORMAT)
    @Mapping(target = "authorId", source = "author.id")
    PieceOfNewsResponseDto newsToNewsResponseDto(PieceOfNews pieceOfNews);

    @Mapping(source = "author", target = "author")
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.content", target = "content")
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    PieceOfNews createNewsDtoToNews(PieceOfNewsCreateDto dto, Author author);

    @Mapping(source = "author", target = "author")
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.content", target = "content")
    @Mapping(source = "dto.id", target = "id")
    @Mapping(target = "lastUpdateDate", ignore = true)
    PieceOfNews updateNewsDtoToNews(PieceOfNewsUpdateDto dto, Author author, LocalDateTime createDate);

}
