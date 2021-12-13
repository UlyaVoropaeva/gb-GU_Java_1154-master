package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildfly.security.password.Password;
import ru.gb.mall.inventory.entity.User;
import ru.gb.mall.inventory.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {

        return userService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<?> saveOrUpdateUser (@PathVariable("id") Long id) {

        return userService.saveOrUpdate(id)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/saveOrUpdateUserEmail/{id}")
    public ResponseEntity<?> saveOrUpdateUserEmail (@PathVariable("id") Long id, @RequestBody String email){
        return userService.saveUserEmail(id, email)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/saveOrUpdateUserPassword/{id}")
    public ResponseEntity<?> saveOrUpdateUserPassword (@PathVariable("id") Long id, @RequestBody Password password){
        return userService.saveUserPassword(id, password)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PostMapping("/registration")
    public ResponseEntity<?> registrationNewUser(@RequestBody User user) {

            return  userService.registrationNewUser(user)
                    ? new ResponseEntity<>(HttpStatus.CREATED)
                    : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {

        return userService.userLogin(user)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
