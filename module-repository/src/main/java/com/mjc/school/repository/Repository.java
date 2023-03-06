package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.PieceOfNews;

import java.util.Map;

public interface Repository {

    Map<Long, PieceOfNews> readAll();
    PieceOfNews readById(long id);
    Boolean deletePieceOfNewsById(long id);

    PieceOfNews create(PieceOfNews pieceOfNews);

    Author getAuthorById(long authorId);

    PieceOfNews update(PieceOfNews pieceOfNews);
}
