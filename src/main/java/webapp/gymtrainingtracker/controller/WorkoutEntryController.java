package webapp.gymtrainingtracker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.entity.WorkoutEntry;
import webapp.gymtrainingtracker.service.ExerciseService;
import webapp.gymtrainingtracker.service.UserService;
import webapp.gymtrainingtracker.service.WorkoutEntryService;

@Controller
public class WorkoutEntryController {

    private final UserService userService;
    private final WorkoutEntryService workoutEntryService;
    private final ExerciseService exerciseService;

    public WorkoutEntryController(WorkoutEntryService workoutEntryService, ExerciseService exerciseService, UserService userService) {
        this.workoutEntryService = workoutEntryService;
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    @GetMapping("/addworkouts")
    public String getAllExercises(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        List < Exercise > exercises = exerciseService.getExercisesForUser(loggedInUser);

        model.addAttribute("exercises", exercises);
        return "add-workouts";
    }

    @GetMapping("/save")
    public String getAllWorkouts(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        List < WorkoutEntry > workouts = workoutEntryService.getWorkoutEntriesByUser(loggedInUser);

        model.addAttribute("workouts", workouts);

        return "saved-workouts";
    }

    @PostMapping("/save")
    public String saveWorkout(@ModelAttribute WorkoutEntry workoutEntry, @RequestParam("selectedExercise") String selectedExercise) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        workoutEntry.setUser(loggedInUser); // loggedInUser predstavlja trenutno ulogovanog korisnika
        workoutEntry.setExerciseName(selectedExercise);
        workoutEntryService.saveWorkoutEntry(workoutEntry);

        return "redirect:/save";
    }

    @PostMapping("/delete/{id}")
    public String deleteWorkout(@PathVariable Long id) {
        workoutEntryService.deleteWorkoutEntry(id);
        return "redirect:/save";
    }

    @GetMapping("/filter")
    public String filterWorkoutsByDate(@RequestParam("filterDate") String filterDate, Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(filterDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        List < WorkoutEntry > filteredWorkouts = workoutEntryService.filterWorkoutsByDateAndUser(date, loggedInUser);

        model.addAttribute("workouts", filteredWorkouts);

        return "saved-workouts";
    }

    @GetMapping("/resetfilter")
    public String resetFilter(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        List < WorkoutEntry > workouts = workoutEntryService.getWorkoutEntriesByUser(loggedInUser);

        model.addAttribute("workouts", workouts);

        return "saved-workouts";
    }

    @GetMapping("/edit/{id}")
    public String editWorkout(@PathVariable Long id, Model model) {
        WorkoutEntry workoutEntry = workoutEntryService.getWorkoutEntryById(id);
        model.addAttribute("workout", workoutEntry);
        return "edit-workout";
    }

    @PostMapping("/update/{id}")
    public String updateWorkout(@PathVariable Long id, @ModelAttribute WorkoutEntry updatedWorkout) {
        workoutEntryService.updateWorkoutEntry(id, updatedWorkout);
        return "redirect:/save";
    }

}