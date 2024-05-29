package exercise.dto.users;

import exercise.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
public class UserPage {
    private User user;

    public UserPage(User user) {
        this.user = user;
    }

    public User user() {
        return user;
    }
}
// END
