package fact.it.p4_backend.service;

import fact.it.p4_backend.exception.UserNotFoundException;

import java.util.Collection;

public interface UserServiceInterface<User> {
     Collection<User> getAll() throws Exception;
     User getById(Long entityId) throws Exception;
     User create(User entity) throws Exception;
     User update(User entity) throws Exception;
     User deleteById(Long entityId) throws Exception;
}