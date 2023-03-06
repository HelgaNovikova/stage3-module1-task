package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.PieceOfNewsModel;

import java.util.Map;

public final class RepositoryUtils {

    public static boolean isAuthorValid(String author) {
        return author.length() > 2 && author.length() < 16;
    }

    public static boolean isTitleValid(String title) {
        return title.length() > 4 && title.length() < 31;
    }

    public static boolean isContentValid(String content) {
        return content.length() > 4 && content.length() < 256;
    }

    public static void validateTitle(String title) {
        if (!isTitleValid(title)) {
            throw new TitleLengthException("News title can not be less than 5 and more than 30 symbols. News title is " + title);
        }
    }

    public static void validateContent(String content) {
        if (!isContentValid(content)) {
            throw new ContentLengthException("News content can not be less than 5 and more than 255 symbols. News content is " + content);
        }
    }

    public static void validateAuthor(String author) {
        if (!isAuthorValid(author)) {
            throw new AuthorNameException("");
        }
    }

    public static void validateNewsPresence(long id, Map<Long, PieceOfNewsModel> news) {
        if (!news.containsKey(id)) {
            throw new NewsNotFoundException(" News with id " + id + " does not exist.");
        }
    }

    private RepositoryUtils() {
        throw new UnsupportedOperationException();
    }

    public static void validateAuthorPresence(AuthorModel author) {
        if (author == null) {
            throw new IllegalArgumentException("Should not be null");
        }
    }
}
