package com.mjc.school.repository;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.PieceOfNews;

import java.util.List;
import java.util.Map;

public interface Repository {
    boolean isAuthorExistsById(Long id);

    Map<Long, PieceOfNews> getNews();
    PieceOfNews getPieceOfNewsById(long id);
    boolean deletePieceOfNewsById(long id);
  //  PieceOfNews updatePieceOfNewsById(PieceOfNews news);

    PieceOfNews save(PieceOfNews pieceOfNews);

  //  PieceOfNews createPieceOfNews(PieceOfNews news);
    Map<Long, Author> getAuthors();

    Author getAuthorById(long authorId);
}
