package webapp.gymtrainingtracker.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import webapp.gymtrainingtracker.dto.UserDto;
import webapp.gymtrainingtracker.entity.Role;
import webapp.gymtrainingtracker.entity.User;
import webapp.gymtrainingtracker.repository.RoleRepository;
import webapp.gymtrainingtracker.repository.UserRepository;
import webapp.gymtrainingtracker.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testSaveUser() {

        Role roleAdmin = Role.builder().id(1L).name("ROLE_ADMIN").build();
        Role roleUser = Role.builder().id(2L).name("ROLE_USER").build();

        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(roleAdmin);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(roleUser);

        UserDto userDto = UserDto.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .password("password123")
            .build();

        userService.saveUser(userDto);

        verify(userRepository, times(1)).save(any(User.class));

        verify(roleRepository, times(0)).save(any(Role.class));
    }

    @Test
    public void testFindAllUsers() {

        User user = User.builder()
            .name("John Doe")
            .email("john.doe@example.com")
            .build();

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List < UserDto > users = userService.findAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void testConvertEntityToDto() {

        User user = User.builder()
            .name("John Doe")
            .email("johndoe@example.com")
            .build();

        UserDto userDto = userService.convertEntityToDto(user);

        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("johndoe@example.com", userDto.getEmail());
    }

    @Test
    void testCheckRoleExist() {

        Role roleAdmin = Role.builder().id(1L).name("ROLE_ADMIN").build();
        Role roleUser = Role.builder().id(2L).name("ROLE_USER").build();

        List < Role > rolesToSave = new ArrayList < > ();
        rolesToSave.add(roleAdmin);
        rolesToSave.add(roleUser);

        when(roleRepository.saveAll(anyList())).thenReturn(rolesToSave);

        List < Role > savedRoles = userService.checkRoleExist();

        verify(roleRepository, times(1)).saveAll(anyList());

        assertEquals(rolesToSave.size(), savedRoles.size());
        for (int i = 0; i < rolesToSave.size(); i++) {
            assertEquals(rolesToSave.get(i), savedRoles.get(i));
        }
    }
}