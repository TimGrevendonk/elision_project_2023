package fact.it.p4_backend.service;

import java.util.Collection;

public interface UserService<T> {
    public Collection<T> getAll() throws Exception;
    public T getById(Long entityId) throws Exception;
    public T create(T entity) throws Exception;
    public T update(T entity) throws Exception;
    public T deleteById(Long entityId) throws Exception;
}
