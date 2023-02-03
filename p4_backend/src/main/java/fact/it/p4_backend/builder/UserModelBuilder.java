package fact.it.p4_backend.builder;

import fact.it.p4_backend.model.User;

public class UserModelBuilder {
    private final String mail;
    private final String name;
    private final String password;
    private String address;
    private String phoneNumber;

    public UserModelBuilder(String mail, String name, String password) {
        this.mail = mail;
        this.name = name;
        this.password = password;
    }

    public UserModelBuilder address(String address){
        this.address = address;
        return this;
    }

    public UserModelBuilder phoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User build(){
        return new User(this);
    }

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
