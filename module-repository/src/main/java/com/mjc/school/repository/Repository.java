package com.mjc.school.repository;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;

import java.util.List;

public interface Repository {

    List<PieceOfNewsModel> readAll();
    PieceOfNewsModel readById(Long id);
    Boolean deletePieceOfNewsById(Long id);

    PieceOfNewsModel create(PieceOfNewsModel pieceOfNews);

    AuthorModel getAuthorById(Long authorId);

    PieceOfNewsModel update(PieceOfNewsModel pieceOfNews);
}
