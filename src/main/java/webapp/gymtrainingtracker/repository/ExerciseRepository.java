package webapp.gymtrainingtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;

@Repository
public interface ExerciseRepository extends JpaRepository < Exercise, Long > {

    List < Exercise > findByUser(User user);
}