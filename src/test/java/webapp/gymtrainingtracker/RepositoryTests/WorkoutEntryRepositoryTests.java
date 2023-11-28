package webapp.gymtrainingtracker.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.entity.WorkoutEntry;
import webapp.gymtrainingtracker.repository.UserRepository;
import webapp.gymtrainingtracker.repository.WorkoutEntryRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class WorkoutEntryRepositoryTests {

    @Autowired
    private WorkoutEntryRepository workoutEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUser_ReturnsWorkoutEntries() {

        User user = User.builder()
            .name("John Doe")
            .email("user@example.com")
            .password("password123")
            .build();

        userRepository.save(user);

        WorkoutEntry workoutEntry1 = WorkoutEntry.builder()
            .user(user)
            .date(Date.valueOf("2023-10-26"))
            .build();

        workoutEntryRepository.save(workoutEntry1);

        WorkoutEntry workoutEntry2 = WorkoutEntry.builder()
            .user(user)
            .date(Date.valueOf("2023-10-27"))
            .build();

        workoutEntryRepository.save(workoutEntry2);

        List < WorkoutEntry > foundWorkoutEntries = workoutEntryRepository.findByUser(user);

        assertNotNull(foundWorkoutEntries);
        assertEquals(2, foundWorkoutEntries.size());
    }

    @Test
    public void testFindByUser_NoEntriesFound() {

        User user = User.builder()
            .name("Ime korisnika")
            .email("email@example.com")
            .password("sifra")
            .build();

        userRepository.save(user);

        List < WorkoutEntry > foundWorkoutEntries = workoutEntryRepository.findByUser(user);

        assertNotNull(foundWorkoutEntries);
        assertTrue(foundWorkoutEntries.isEmpty());
    }

    @Test
    public void testFilterWorkoutsByDateAndUser_ReturnsFilteredWorkoutEntries() {

        User user = User.builder()
            .name("John Doe")
            .email("user@example.com")
            .password("password123")
            .build();

        userRepository.save(user);

        WorkoutEntry workoutEntry1 = WorkoutEntry.builder()
            .user(user)
            .date(Date.valueOf("2023-10-26"))
            .build();

        workoutEntryRepository.save(workoutEntry1);

        WorkoutEntry workoutEntry2 = WorkoutEntry.builder()
            .user(user)
            .date(Date.valueOf("2023-10-27"))
            .build();

        workoutEntryRepository.save(workoutEntry2);

        List < WorkoutEntry > filteredWorkoutEntries = workoutEntryRepository.filterWorkoutsByDateAndUser(
            Date.valueOf("2023-10-26"), user);

        assertNotNull(filteredWorkoutEntries);
        assertEquals(1, filteredWorkoutEntries.size());
    }

    @Test
    public void testFilterWorkoutsByDateAndUser_NoEntriesFound() {

        User user = User.builder()
            .name("Ime korisnika")
            .email("email@example.com")
            .password("sifra")
            .build();

        userRepository.save(user);

        List < WorkoutEntry > filteredWorkoutEntries = workoutEntryRepository.filterWorkoutsByDateAndUser(
            Date.valueOf("2023-10-26"), user);

        assertNotNull(filteredWorkoutEntries);
        assertTrue(filteredWorkoutEntries.isEmpty());
    }
}