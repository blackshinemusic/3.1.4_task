package ru.kata.spring.boot_security.demo.Init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Set;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Init(UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Set<Role> userRoles = Set.of(userRole);
        Set<Role> adminRoles = Set.of(adminRole, userRole);

        User user = new User(
                "vova",
                "$2a$12$1TrhXtZcJ1M/r5g0rlBQ3ecFKK1kXdGJyFzwDPrB3nRA8/iGFFmqy",
                "wefwf",
                24,
                adminRoles);
        userRepository.save(user);


    }
}
