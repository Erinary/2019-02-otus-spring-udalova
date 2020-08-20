package ru.otus.erinary.hw08.library.service;

import ru.otus.erinary.hw08.library.dao.model.Author;
import ru.otus.erinary.hw08.library.dao.model.Book;
import ru.otus.erinary.hw08.library.dao.model.Comment;
import ru.otus.erinary.hw08.library.dao.model.Genre;

import java.util.List;

public interface LibraryService {

    List<Author> getAuthors();

    Author getAuthorByName(String name);

    void deleteAuthor(String id);

    List<Genre> getGenres();

    Genre getGenreByName(String name);

    void deleteGenre(Long id);

    List<Book> getBooks();

    Book getBookById(Long id);

    Book saveBook(Long id, String title, int year, String authorName, String genreName);

    void deleteBook(Long id);

    List<Comment> getBookComments(Long bookId);

    Long saveComment(String text, String user, Long bookId);

    void deleteComment(Long id);
}
