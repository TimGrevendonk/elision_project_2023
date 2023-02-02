package fact.it.p4_backend.service;

import java.util.Collection;

public interface UserServiceInterface<User, UserSecureDTO> {
     Collection<UserSecureDTO> getAll() throws Exception;
     UserSecureDTO getById(Long userId) throws Exception;
     UserSecureDTO create(User user) throws Exception;
     UserSecureDTO update(User user) throws Exception;
     UserSecureDTO deleteById(Long entityId);
}