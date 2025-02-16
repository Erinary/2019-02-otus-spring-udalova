package ru.otus.erinary.hw06.hibernatelibrary.dao.genre;

import org.springframework.stereotype.Repository;
import ru.otus.erinary.hw06.hibernatelibrary.model.Genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

/**
 * Realization of {@link GenreRepository}.
 */
@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Long insert(final Genre genre) {
        manager.persist(genre);
        return genre.getId();
    }

    @Override
    public void update(final Genre genre) {
        manager.merge(genre);
    }

    @Override
    public Optional<Genre> findById(final Long id) {
        return Optional.ofNullable(manager.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> findByName(final String name) {
        var query = manager.createQuery("select g from Genre g where g.name = :name", Genre.class);
        query.setParameter("name", name);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> findIdByName(final String name) {
        var query = manager.createQuery("select g.id from Genre g where g.name = :name", Long.class);
        query.setParameter("name", name);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Genre> findAll() {
        return manager.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public void delete(final Long id) {
        var query = manager.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
