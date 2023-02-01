package fact.it.p4_backend.builder;

import fact.it.p4_backend.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserModelBuilder {
    public final String mail;
    public final String name;
    public final String password;
    public String address;
    public  String phoneNumber;

    public final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserModelBuilder(String mail, String name, String password) {
        this.mail = mail;
        this.name = name;
        this.password = this.passwordEncoder.encode(password);
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
        User user = new User(this);
        return user;
    }
}
