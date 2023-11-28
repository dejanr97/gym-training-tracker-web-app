package webapp.gymtrainingtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.repository.ExerciseRepository;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List < Exercise > getExercisesForUser(User user) {

        List < Exercise > exercises = exerciseRepository.findByUser(user);
        return exercises;
    }

    @Override
    public void saveExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

}