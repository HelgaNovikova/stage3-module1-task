package com.mjc.school.repository;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.util.List;

public interface Repository<T> {

    List<T> readAll();
    T readById(Long id);
    Boolean deletePieceOfNewsById(Long id);

    T create(NewsModel pieceOfNews);

    AuthorModel getAuthorById(Long authorId);

    T update(NewsModel pieceOfNews);
}
