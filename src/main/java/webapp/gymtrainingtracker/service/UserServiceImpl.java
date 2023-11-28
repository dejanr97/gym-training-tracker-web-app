package webapp.gymtrainingtracker.service;

import webapp.gymtrainingtracker.dto.UserDto;
import webapp.gymtrainingtracker.entity.Role;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.repository.RoleRepository;
import webapp.gymtrainingtracker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role roleUser = roleRepository.findByName("ROLE_USER");

        if (roleAdmin == null) {

            roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleAdmin = roleRepository.save(roleAdmin);

        }

        if (roleUser == null) {

            roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleUser = roleRepository.save(roleUser);
        }

        user.setRoles(Arrays.asList(roleAdmin, roleUser));
        userRepository.save(user);
    }

    @Override
    public List < UserDto > findAllUsers() {
        List < User > users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
            .collect(Collectors.toList());
    }
    @Override
    public UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public List < Role > checkRoleExist() {
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setName("ROLE_USER");
        role2.setName("ROLE_ADMIN");

        List < Role > rolesToSave = Arrays.asList(role1, role2);

        return roleRepository.saveAll(rolesToSave);
    }

}