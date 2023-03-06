package com.mjc.school.repository.utils;

import com.mjc.school.repository.impl.FileRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class DataSource {
    public static final int DEFAULT_NEWS_COUNT_TO_GENERATE = 20;

    private List<AuthorModel> readAuthorsFromFile(String path) {
        List<String> lines = readFromFile(path);
        long id = 1;
        List<AuthorModel> authorsFromFile = new ArrayList<>();
        for (String line : lines) {
            if (RepositoryUtils.isAuthorValid(line)) {
                authorsFromFile.add(new AuthorModel(id, line));
                id++;
            }
        }
        return authorsFromFile;
    }


    private List<String> readTitlesFromFiles(String titlePath) {
        List<String> lines = readFromFile(titlePath);
        lines.forEach(RepositoryUtils::isTitleValid);
        return lines;
    }

    private List<String> readContentsFromFile(String contentPath) {
        List<String> lines = readFromFile(contentPath);
        lines.forEach(RepositoryUtils::isContentValid);
        return lines;
    }

    private List<String> readFromFile(String contentPath) {
        try {
            URL resource = Objects.requireNonNull(FileRepository.class.getClassLoader().getResource(contentPath));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
                return new ArrayList<>(reader.lines().toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + contentPath, e);
        }
    }

    private final Map<Long, PieceOfNewsModel> news = new HashMap<>();

    public Map<Long, PieceOfNewsModel> getNews() {
        return news;
    }

    private final Map<Long, AuthorModel> authors = new HashMap<>();

    public Map<Long, AuthorModel> getAuthors() {
        return authors;
    }

    private long lastNewsIndex = 0;

    public long getNextId() {
        return ++lastNewsIndex;
    }

    public void generateNews(String titlesPath, String authorsPath, String contentPath) {
        List<String> titlesFromFile = readTitlesFromFiles(titlesPath);
        List<AuthorModel> authorsFromFile = readAuthorsFromFile(authorsPath);
        List<String> contentFromFile = readContentsFromFile(contentPath);
        for (int i = 0; i < DEFAULT_NEWS_COUNT_TO_GENERATE; i++) {
            Random randomNum = new Random();
            int indexC = randomNum.nextInt(0, contentFromFile.size());
            String tmpContent = contentFromFile.get(indexC);
            int indexT = randomNum.nextInt(0, titlesFromFile.size());
            String tmpTitle = titlesFromFile.get(indexT);
            int indexA = randomNum.nextInt(0, authorsFromFile.size());
            AuthorModel tmpAuthor = authorsFromFile.get(indexA);
            long id = this.lastNewsIndex++;
            LocalDateTime now = LocalDateTime.now();
            news.put(id, new PieceOfNewsModel(id, tmpTitle, tmpContent, now, now, tmpAuthor));
            authors.put(tmpAuthor.getId(), tmpAuthor);
            contentFromFile.remove(indexC);
            titlesFromFile.remove(indexT);
            authorsFromFile.remove(indexA);
        }
    }

}
