package ua.nure.server.database.repository;

import java.util.List;

import ua.nure.domain.entity.Entity;
import ua.nure.server.exception.RepositoryException;

public interface IRepository <T extends Entity> {
    List<T> getAll() throws RepositoryException;
    T getById(Integer id) throws RepositoryException;
}
