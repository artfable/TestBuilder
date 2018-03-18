package org.artfable.test.builder.core.service;

import org.artfable.test.builder.core.data.UserRepository;
import org.artfable.test.builder.core.model.AppUser;
import org.artfable.test.builder.core.model.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author artfable
 * 26.12.17
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    public AppUser getCurrentUser() {
        UserDetailsDto user = (UserDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findOne(user.getId());
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser createNewUser(AppUser user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new IllegalArgumentException("User with " + user.getLogin() + " already exist");
        }
        AppUser userToSave = new AppUser(null, user.getLogin(),
                passwordEncoder.encodePassword(user.getPassword(), "some"),
                user.getName(), user.getLastName(), user.getPatronymic(), Collections.emptyList());
        return userRepository.saveAndFlush(userToSave);
    }

    public AppUser updateUser(AppUser user) {
        AppUser userToSave = new AppUser(user.getId(), user.getLogin(),
                passwordEncoder.encodePassword(user.getPassword(), "some"),
                user.getName(), user.getLastName(), user.getPatronymic(), Collections.emptyList());
        return userRepository.saveAndFlush(userToSave);
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
