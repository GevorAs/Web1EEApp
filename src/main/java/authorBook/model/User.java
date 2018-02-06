package authorBook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private Gender gender;
    private String password;

    public User(String name, String surname, String email, Gender gender, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }

    public User(long id, String name, String surname, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
    }
}
