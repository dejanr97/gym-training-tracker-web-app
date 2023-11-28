package webapp.gymtrainingtracker.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.repository.ExerciseRepository;
import webapp.gymtrainingtracker.service.ExerciseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceTests {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseServiceImpl exerciseService;

    @Test
    public void testGetExercisesForUser() {

        User user = User.builder()
            .name("Test User")
            .email("test@example.com")
            .password("password")
            .build();

        Exercise exercise = Exercise.builder()
            .newExerciseName("Exercise 1")
            .user(user)
            .build();

        when(exerciseRepository.findByUser(user)).thenReturn(Collections.singletonList(exercise));

        List < Exercise > exercises = exerciseService.getExercisesForUser(user);

        assertNotNull(exercises);
        assertEquals(1, exercises.size());
    }

    @Test
    public void testSaveExercise() {

        Exercise exercise = Exercise.builder()
            .newExerciseName("Exercise 1")
            .build();

        exerciseService.saveExercise(exercise);

        verify(exerciseRepository, times(1)).save(exercise);
    }

    @Test
    public void testDeleteExercise() {

        Long exerciseId = 1L;

        exerciseService.deleteExercise(exerciseId);

        verify(exerciseRepository, times(1)).deleteById(exerciseId);
    }
}