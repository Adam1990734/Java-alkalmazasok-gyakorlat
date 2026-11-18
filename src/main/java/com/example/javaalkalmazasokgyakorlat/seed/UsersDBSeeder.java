package com.example.javaalkalmazasokgyakorlat.seed;

import com.example.javaalkalmazasokgyakorlat.model.user.User;
import com.example.javaalkalmazasokgyakorlat.model.user.UserRole;
import com.example.javaalkalmazasokgyakorlat.repository.UserRepository;
import com.example.javaalkalmazasokgyakorlat.repository.UserRoleRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class UsersDBSeeder {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final ClassPathResource userFilePath = new ClassPathResource("seed/users.txt");
    private final ClassPathResource userRoleFilePath = new ClassPathResource("seed/roles.txt");

    private final Map<Integer, UserRole> userRoles = new HashMap<>();

    public UsersDBSeeder(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void seed() throws IOException {
        if(userRoleRepository.count() == 0)
            loadRoles();
        if(userRepository.count() == 0)
            loadUsers();
    }

    private void loadUsers() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     this.userFilePath.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t");

                User user = new User();
                user.setUsername(parts[1]);
                user.setPassword(passwordEncoder.encode(parts[2]));
                user.setRole(
                        this.userRoles.get(Integer.parseInt(parts[3]))
                );

                userRepository.save(user);
            }
        }
    }
    private void loadRoles() throws IOException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     this.userRoleFilePath.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t");

                Integer id = Integer.parseInt(parts[0]);

                UserRole role = new UserRole();
                role.setId(id);
                role.setName(parts[1]);

                this.userRoles.put(id, role);

                userRoleRepository.save(role);
            }
        }
    }
}
