package webapp.gymtrainingtracker.service;

import java.util.Date;
import java.util.List;
import webapp.gymtrainingtracker.entity.WorkoutEntry;
import webapp.gymtrainingtracker.entity.User;

public interface WorkoutEntryService {

    List < WorkoutEntry > getAllWorkoutEntries();

    WorkoutEntry getWorkoutEntryById(Long id);

    void saveWorkoutEntry(WorkoutEntry workoutEntry);

    void deleteWorkoutEntry(Long id);

    void updateWorkoutEntry(Long id, WorkoutEntry updatedWorkout);

    List < WorkoutEntry > filterWorkoutsByDateAndUser(Date filterDate, User user);

    List < WorkoutEntry > getWorkoutEntriesByUser(User user);
}