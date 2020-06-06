package ru.otus.erinary.hw06.hibernatelibrary.dao.book;

import org.springframework.stereotype.Repository;
import ru.otus.erinary.hw06.hibernatelibrary.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Имплементация {@link BookRepository}
 */
@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Book save(final Book book) {
        if (book.getId() == null) {
            manager.persist(book);
        } else {
            manager.merge(book);
        }
        return book;
    }

    @Override
    public Optional<Book> findById(final Long id) {
        var query = manager.createQuery("select b from Book b where b.id = :id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return manager.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public List<Book> findAllByAuthorId(final Long authorId) {
        var query = manager.createQuery("select b from Book b where b.author.id = :author_id", Book.class);
        query.setParameter("author_id", authorId);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByGenreId(final Long genreId) {
        var query = manager.createQuery("select b from Book b where b.genre.id = :genre_id", Book.class);
        query.setParameter("genre_id", genreId);
        return query.getResultList();
    }

    @Override
    public void delete(final Long id) {
        var query = manager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
