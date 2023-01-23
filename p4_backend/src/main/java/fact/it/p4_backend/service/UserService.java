package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;
import fact.it.p4_backend.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService<T> {
    public Collection<T> getAll() throws Exception;
    public T getById(Long entityId) throws Exception;
    public T create(T entity) throws Exception;
    public void update(T entity) throws Exception;
    public void deleteById(Long entityId) throws Exception;
}
