package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;
import com.mjc.school.repository.utils.DataSource;

import java.time.LocalDateTime;
import java.util.List;

public class FileRepository implements Repository<PieceOfNewsModel> {

    private final DataSource dataSource;

    public FileRepository(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public AuthorModel getAuthorById(Long authorId) {
        return dataSource.getAuthors().get(authorId);
    }

    @Override
    public List<PieceOfNewsModel> readAll() {
        return dataSource.getNews().values().stream().toList();
    }

    @Override
    public PieceOfNewsModel readById(Long id) {
        return dataSource.getNews().get(id);
    }

    @Override
    public Boolean deletePieceOfNewsById(Long id) {
        return dataSource.getNews().remove(id) != null;
    }

    @Override
    public PieceOfNewsModel create(PieceOfNewsModel pieceOfNews) {
        return save(pieceOfNews);
    }

    @Override
    public PieceOfNewsModel update(PieceOfNewsModel pieceOfNews) {
        return save(pieceOfNews);
    }

    private PieceOfNewsModel save(PieceOfNewsModel pieceOfNews) {
        if (pieceOfNews.getId() == null) {
            pieceOfNews.setId(dataSource.getNextId());
            pieceOfNews.setCreateDate(LocalDateTime.now());
        }
        dataSource.getNews().put(pieceOfNews.getId(), pieceOfNews);
        pieceOfNews.setLastUpdateDate(LocalDateTime.now());
        return pieceOfNews;
    }
}
