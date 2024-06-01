package exercise.dto.users;

import exercise.model.User;
import java.util.List;

// BEGIN
public record UsersPage(String term, List<User> users) {
}
// END
