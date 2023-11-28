package webapp.gymtrainingtracker.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.repository.ExerciseRepository;
import webapp.gymtrainingtracker.repository.UserRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ExerciseRepositoryTests {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUser() {

        User user = User.builder()
            .name("testUser")
            .email("test@example.com")
            .password("password")
            .build();

        Exercise exercise1 = Exercise.builder()
            .newExerciseName("Exercise 1")
            .user(user)
            .build();

        Exercise exercise2 = Exercise.builder()
            .newExerciseName("Exercise 2")
            .user(user)
            .build();

        user = userRepository.save(user);
        exercise1 = exerciseRepository.save(exercise1);
        exercise2 = exerciseRepository.save(exercise2);

        List < Exercise > exercises = exerciseRepository.findByUser(user);

        assertEquals(2, exercises.size());
    }
}