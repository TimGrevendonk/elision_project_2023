package fact.it.p4_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setid(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
