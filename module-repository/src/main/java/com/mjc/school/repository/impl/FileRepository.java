package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.PieceOfNews;
import com.mjc.school.repository.utils.DataSource;
import com.mjc.school.repository.utils.RepositoryUtils;

import java.time.LocalDateTime;
import java.util.Map;

public class FileRepository implements Repository {

    private final DataSource dataSource;

    public FileRepository(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public Author getAuthorById(long authorId) {
        return dataSource.getAuthors().get(authorId);
    }

    @Override
    public Map<Long, PieceOfNews> readAll() {
        return dataSource.getNews();
    }

    @Override
    public PieceOfNews readById(long id) {
        RepositoryUtils.validateNewsPresence(id, dataSource.getNews());
        return dataSource.getNews().get(id);
    }

    @Override
    public Boolean deletePieceOfNewsById(long id) {
        RepositoryUtils.validateNewsPresence(id, dataSource.getNews());
        return dataSource.getNews().remove(id) != null;
    }

    @Override
    public PieceOfNews create(PieceOfNews pieceOfNews) {
        return save(pieceOfNews);
    }

    @Override
    public PieceOfNews update(PieceOfNews pieceOfNews) {
        return save(pieceOfNews);
    }

    private PieceOfNews save(PieceOfNews pieceOfNews) {
        if (pieceOfNews.getId() != null) {
            RepositoryUtils.validateNewsPresence(pieceOfNews.getId(), dataSource.getNews());
        }
        RepositoryUtils.validateContent(pieceOfNews.getContent());
        RepositoryUtils.validateTitle(pieceOfNews.getTitle());
        RepositoryUtils.validateAuthorPresence(pieceOfNews.getAuthor());

        if (pieceOfNews.getId() == null) {
            pieceOfNews.setId(dataSource.getNextId());
            pieceOfNews.setCreateDate(LocalDateTime.now());
        }
        dataSource.getNews().put(pieceOfNews.getId(), pieceOfNews);
        pieceOfNews.setLastUpdateDate(LocalDateTime.now());
        return pieceOfNews;
    }
}
