package webapp.gymtrainingtracker.RepositoryTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {

        User user = User.builder()
            .name("Test User")
            .email("test@example.com")
            .password("password")
            .build();
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("test@example.com");

        assertNotNull(foundUser);
        assertEquals("Test User", foundUser.getName());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    public void testFindByEmail_NotFound() {

        User foundUser = userRepository.findByEmail("non_existing@example.com");

        assertNull(foundUser);
    }
}