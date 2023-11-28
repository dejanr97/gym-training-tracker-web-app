package webapp.gymtrainingtracker.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.entity.WorkoutEntry;
import webapp.gymtrainingtracker.repository.WorkoutEntryRepository;

@Service
public class WorkoutEntryServiceImpl implements WorkoutEntryService {

    private final WorkoutEntryRepository workoutEntryRepository;

    @Autowired
    public WorkoutEntryServiceImpl(WorkoutEntryRepository workoutEntryRepository) {
        this.workoutEntryRepository = workoutEntryRepository;
    }

    @Override
    public List < WorkoutEntry > getWorkoutEntriesByUser(User user) {
        return workoutEntryRepository.findByUser(user);
    }

    @Override
    public List < WorkoutEntry > getAllWorkoutEntries() {
        return workoutEntryRepository.findAll();
    }

    @Override
    public WorkoutEntry getWorkoutEntryById(Long id) {
        return workoutEntryRepository.findById(id).orElse(null);
    }

    @Override
    public void saveWorkoutEntry(WorkoutEntry workoutEntry) {
        workoutEntryRepository.save(workoutEntry);
    }

    @Override
    public void deleteWorkoutEntry(Long id) {
        workoutEntryRepository.deleteById(id);
    }

    @Override
    public List < WorkoutEntry > filterWorkoutsByDateAndUser(Date filterDate, User user) {
        return workoutEntryRepository.filterWorkoutsByDateAndUser(filterDate, user);
    }

    @Override
    public void updateWorkoutEntry(Long id, WorkoutEntry updatedWorkout) {
        WorkoutEntry existingWorkout = workoutEntryRepository.findById(id).orElse(null);
        if (existingWorkout != null) {

            existingWorkout.setDate(updatedWorkout.getDate());
            existingWorkout.setExerciseType(updatedWorkout.getExerciseType());
            existingWorkout.setExerciseName(updatedWorkout.getExerciseName());
            existingWorkout.setSets(updatedWorkout.getSets());
            existingWorkout.setReps(updatedWorkout.getReps());
            existingWorkout.setWeight(updatedWorkout.getWeight());

            workoutEntryRepository.save(existingWorkout);
        }
    }

}