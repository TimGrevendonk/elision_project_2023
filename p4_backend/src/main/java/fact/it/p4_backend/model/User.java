package fact.it.p4_backend.model;

import fact.it.p4_backend.builder.UserModelBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String name;
    private  String mail;
    private  String password;
    private  String address;
    private  String phoneNumber;

    public User() {
    }

    public User(UserModelBuilder builder){
        this.name = builder.name;
        this.mail = builder.mail;
        this.password = builder.password;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public Long getId() {
        return id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
