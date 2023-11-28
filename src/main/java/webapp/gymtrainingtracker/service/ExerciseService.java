package webapp.gymtrainingtracker.service;

import java.util.List;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;

public interface ExerciseService {

    void saveExercise(Exercise exercise);

    void deleteExercise(Long id);

    List < Exercise > getExercisesForUser(User user);

    Exercise getExerciseById(Long id);

}