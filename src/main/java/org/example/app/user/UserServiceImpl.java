package org.example.app.user;

import lombok.extern.slf4j.Slf4j;
import org.example.app.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationService notificationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.notificationService = notificationService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if (userByUsername.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        User user = userByUsername.get();

        return user;
    }

    @Override
    public void resetUserPassword(String username, String newPassword) {

        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        if (userByUsername.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        User user = userByUsername.get();

        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
        log.info("Password was succesfully for user [%s]".formatted(username));

        String email = user.getEmail();
        String body = "Your password has been reset to " + newPassword;
        String subject = "Reset Password";
        notificationService.sendNotification(email, subject, body);

    }

}
