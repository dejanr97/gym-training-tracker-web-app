package webapp.gymtrainingtracker.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.entity.WorkoutEntry;
import webapp.gymtrainingtracker.repository.WorkoutEntryRepository;
import webapp.gymtrainingtracker.service.WorkoutEntryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class WorkoutEntryServiceTests {

    @Mock
    private WorkoutEntryRepository workoutEntryRepository;

    @InjectMocks
    private WorkoutEntryServiceImpl workoutEntryService;

    @Test
    public void testGetAllWorkoutEntries() {

        List < WorkoutEntry > expectedEntries = new ArrayList < > ();
        expectedEntries.add(WorkoutEntry.builder().id(1L).build());
        expectedEntries.add(WorkoutEntry.builder().id(2L).build());
        expectedEntries.add(WorkoutEntry.builder().id(3L).build());

        when(workoutEntryRepository.findAll()).thenReturn(expectedEntries);

        List < WorkoutEntry > result = workoutEntryService.getAllWorkoutEntries();

        assertEquals(expectedEntries, result);
        verify(workoutEntryRepository).findAll();
    }

    @Test
    public void testGetWorkoutEntryById() {

        Long entryId = 1L;
        WorkoutEntry expectedEntry = WorkoutEntry.builder().id(entryId).build();

        when(workoutEntryRepository.findById(entryId)).thenReturn(Optional.of(expectedEntry));

        WorkoutEntry result = workoutEntryService.getWorkoutEntryById(entryId);

        assertEquals(expectedEntry, result);
        verify(workoutEntryRepository).findById(entryId);
    }

    @Test
    public void testGetWorkoutEntryByIdNotFound() {
        Long entryId = 1L;
        when(workoutEntryRepository.findById(entryId)).thenReturn(Optional.empty());

        WorkoutEntry result = workoutEntryService.getWorkoutEntryById(entryId);

        assertNull(result);
        verify(workoutEntryRepository).findById(entryId);
    }

    @Test
    public void testSaveWorkoutEntry() {

        WorkoutEntry entry = WorkoutEntry.builder().id(1L).build();

        workoutEntryService.saveWorkoutEntry(entry);

        verify(workoutEntryRepository, times(1)).save(entry);
    }

    @Test
    public void testDeleteWorkoutEntry() {
        Long entryId = 1L;

        workoutEntryService.deleteWorkoutEntry(entryId);

        verify(workoutEntryRepository, times(1)).deleteById(entryId);
    }

    @Test
    public void testFilterWorkoutsByDateAndUser() {

        Date filterDate = new Date();
        User user = User.builder().id(1L).build();

        List < WorkoutEntry > expectedEntries = new ArrayList < > ();
        expectedEntries.add(WorkoutEntry.builder().id(1L).build());
        expectedEntries.add(WorkoutEntry.builder().id(2L).build());
        expectedEntries.add(WorkoutEntry.builder().id(3L).build());

        when(workoutEntryRepository.filterWorkoutsByDateAndUser(filterDate, user)).thenReturn(expectedEntries);

        List < WorkoutEntry > result = workoutEntryService.filterWorkoutsByDateAndUser(filterDate, user);

        assertEquals(expectedEntries, result);
        verify(workoutEntryRepository).filterWorkoutsByDateAndUser(filterDate, user);
    }

    @Test
    public void testUpdateWorkoutEntry() {

        Long id = 1L;
        WorkoutEntry existingWorkout = new WorkoutEntry();
        WorkoutEntry updatedWorkout = new WorkoutEntry();

        Mockito.when(workoutEntryRepository.findById(id)).thenReturn(Optional.of(existingWorkout));

        workoutEntryService.updateWorkoutEntry(id, updatedWorkout);

        assertEquals(existingWorkout.getDate(), updatedWorkout.getDate());
        assertEquals(existingWorkout.getExerciseType(), updatedWorkout.getExerciseType());
        assertEquals(existingWorkout.getExerciseName(), updatedWorkout.getExerciseName());
        assertEquals(existingWorkout.getSets(), updatedWorkout.getSets());
        assertEquals(existingWorkout.getReps(), updatedWorkout.getReps());
        assertEquals(existingWorkout.getWeight(), updatedWorkout.getWeight(), 0.001);
        Mockito.verify(workoutEntryRepository).save(existingWorkout);
    }
}