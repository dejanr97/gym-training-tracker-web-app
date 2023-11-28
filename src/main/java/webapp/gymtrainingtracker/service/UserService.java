package webapp.gymtrainingtracker.service;

import webapp.gymtrainingtracker.dto.UserDto;
import webapp.gymtrainingtracker.entity.Role;
import webapp.gymtrainingtracker.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List < UserDto > findAllUsers();

    UserDto convertEntityToDto(User user);

    List < Role > checkRoleExist();

}