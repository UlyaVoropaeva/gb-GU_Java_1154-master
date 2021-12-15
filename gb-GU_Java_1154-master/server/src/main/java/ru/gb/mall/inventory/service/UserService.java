package ru.gb.mall.inventory.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.wildfly.security.password.Password;
import ru.gb.mall.inventory.entity.User;
import ru.gb.mall.inventory.entity.UsersAccess;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.UserRepository;
import ru.gb.mall.inventory.repository.UsersAccessRepository;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UsersAccessRepository usersAccessRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, UsersAccessRepository usersAccessRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.usersAccessRepository = usersAccessRepository;
        this.encoder = encoder;
    }

    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), true).toList();
    }

    public User findById(long id) {
        try {
            return userRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Users entity no found by id: " + id, e);
        }
    }

    public boolean deleteById(Long userId) {
        try {
            userRepository.deleteById(userId);
            return true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Users entity no found by id: " + userId, e);
        }
    }

    public void saveOrUpdate(User user) {

        try {
            userRepository.save(user);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("User entity no found by id: " + user.getId(), e);
        }

    }

    public boolean saveOrUpdate(Long usersId) {
        User user = findById(usersId);
        if (user == null) {
            return false;
        }
        saveOrUpdate(user);
        return true;
    }



    public  boolean saveUserPassword (Long usersId, Password password){

        try {
            UsersAccess access = findById(usersId).getUsersAccess();
            access.setPassword(password);
            usersAccessRepository.save(access);
            return  true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("User entity no found by id: " + usersId, e);
        }

    }

    public  boolean saveUserEmail (Long usersId, String email) {

        try {
            UsersAccess access = findById(usersId).getUsersAccess();
            access.setEmail(email);
            usersAccessRepository.save(access);
            return true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("User entity no found by id: " + usersId, e);
        }
    }

    private boolean emailExists(String email) {

         return usersAccessRepository.findByEmail(email).isPresent();
    }

    private boolean passwordCheck(Long id, String password) {
       User user = userRepository.getById(id);
        return encoder.matches(password, String.valueOf(user.getUsersAccess().getPassword()));
    }

    public boolean userLogin(User user) {
        if ((!emailExists(user.getUsersAccess().getEmail()))||(!passwordCheck(user.getId(), String.valueOf(user.getUsersAccess().getPassword())))) {
            throw new EntityNotFoundException("Wrong login or password" );
        }
        return true;
    }

    public boolean registrationNewUser(User newUser) {
        if (!userLogin(newUser)) {
            throw new EntityNotFoundException("There is already a user registered with the email provided" );
        }
        User user = new User();
        user.getUsersAccess().setEmail(newUser.getUsersAccess().getEmail());
        user.getUsersAccess().setPassword(newUser.getUsersAccess().getPassword());
        user.setRoles(newUser.getRoles());
        userRepository.save(user);
        usersAccessRepository.save(user.getUsersAccess());
        return  true;
    }
}
