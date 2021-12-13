package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import org.wildfly.security.password.Password;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.ProductPrice;
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

    public UserService(UserRepository userRepository, UsersAccessRepository usersAccessRepository) {
        this.userRepository = userRepository;
        this.usersAccessRepository = usersAccessRepository;
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

    public boolean findByEmail(String email){
        UsersAccess access =  usersAccessRepository.findByEmail(email);

        try {
            userRepository.findById(access.getId()).orElseThrow();
            return  true;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("There is already a user registered with the email provided" , e);
        }
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

}
