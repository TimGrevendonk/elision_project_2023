package fact.it.p4_backend.DTO;

import org.springframework.stereotype.Component;

@Component
public class UserSecureDTO {
    private Long userId;
    private String userName;
    private String userMail;


    public UserSecureDTO(Long userId, String userName, String userMail) {
        this.userName = userName;
        this.userMail = userMail;
        this.userId = userId;
    }

    public UserSecureDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
