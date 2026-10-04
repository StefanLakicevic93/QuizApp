package com.QuizApp.service.user;

import com.QuizApp.entity.User;
import com.QuizApp.enums.UserRole;
import com.QuizApp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @PostConstruct
    private void createAdminAcc() {
        User admin = userRepository.findByRole(UserRole.ADMIN);

        if(admin == null) {
            User adminUser = new User();
            adminUser.setName("Admin");
            adminUser.setEmail("admin@test.com");
            adminUser.setPassword("adminp");
            adminUser.setRole(UserRole.ADMIN);

            userRepository.save(adminUser);

            System.out.println("Admin account created successfully");
        } else {
            System.out.println("Admin account already exists!");
        }
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }

    public User createUser(User user) {
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }

    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if(optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())) {
            return optionalUser.get();
        }
        return null;
    }

}
