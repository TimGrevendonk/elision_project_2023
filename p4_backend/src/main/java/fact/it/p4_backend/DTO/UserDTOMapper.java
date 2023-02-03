package fact.it.p4_backend.DTO;

import fact.it.p4_backend.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOMapper {
    public UserSecureDTO toUserSecureDto(User user){
        Long userId = user.getId();
        String name = user.getName();
        String mail = user.getMail();
        String address = user.getAddress();
        String phoneNumber = user.getPhoneNumber();
        return new UserSecureDTO(userId, name, mail, address, phoneNumber);
    }

    public List<UserSecureDTO> toUserSecureDtoList(List<User> users){
        return users.stream().map(this::toUserSecureDto).collect(Collectors.toList());
    }
}
