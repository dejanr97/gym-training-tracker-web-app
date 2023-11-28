package webapp.gymtrainingtracker.RepositoryTests;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import webapp.gymtrainingtracker.entity.Role;
import webapp.gymtrainingtracker.repository.RoleRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {

        Role role = Role.builder()
            .name("ROLE_TEST")
            .build();
        roleRepository.save(role);

        Role foundRole = roleRepository.findByName("ROLE_TEST");

        assertNotNull(foundRole);
        assertEquals("ROLE_TEST", foundRole.getName());
    }

    @Test
    public void testFindByName_NotFound() {

        Role foundRole = roleRepository.findByName("NON_EXISTING_ROLE");

        assertNull(foundRole);
    }
}