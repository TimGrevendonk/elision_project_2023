package fact.it.p4_backend.DTO;

import org.springframework.stereotype.Component;

@Component
public class UserSecureDTO {
    private Long userId;
    private String userName;
    private String userMail;
    private String address;
    private String phoneNumber;

    public UserSecureDTO(Long userId, String userName, String userMail, String address, String phoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
