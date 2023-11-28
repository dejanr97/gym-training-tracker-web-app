package webapp.gymtrainingtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.entity.WorkoutEntry;

@Repository
public interface WorkoutEntryRepository extends JpaRepository < WorkoutEntry, Long > {

    @Query("SELECT we FROM WorkoutEntry we WHERE we.user = :user AND we.date = :filterDate")
    List < WorkoutEntry > filterWorkoutsByDateAndUser(@Param("filterDate") java.util.Date filterDate, @Param("user") User user);

    List < WorkoutEntry > findByUser(User user);

}