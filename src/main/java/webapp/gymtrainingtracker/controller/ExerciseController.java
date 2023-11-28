package webapp.gymtrainingtracker.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import webapp.gymtrainingtracker.entity.Exercise;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.service.ExerciseService;
import webapp.gymtrainingtracker.service.UserService;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;

    public ExerciseController(ExerciseService exerciseService, UserService userService) {
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    @GetMapping("/addexercises")
    public String addExercises(Model model) {
        model.addAttribute("newExercise", new Exercise());
        return "add-exercises";
    }

    @GetMapping("/exerciseslist")
    public String getAllExercises(Model model) {

        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        List < Exercise > exercises = loggedInUser.getExercises();
        model.addAttribute("exercises", exercises);
        return "exercises-list";
    }

    @PostMapping("/addexercise")
    public String createExercise(@ModelAttribute("newExercise") Exercise exercise) {

        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userService.findByEmail(loggedInUsername);

        exercise.setUser(loggedInUser);
        exerciseService.saveExercise(exercise);

        return "redirect:/exerciseslist";
    }

    @PostMapping("exercises/delete/{id}")
    public String deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return "redirect:/exerciseslist";
    }
}