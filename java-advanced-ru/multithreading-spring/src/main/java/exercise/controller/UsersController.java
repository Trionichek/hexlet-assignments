package exercise.controller;

import exercise.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PatchMapping("/{id}")
    public Mono<User> updateUser(@RequestBody User user, @PathVariable int id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }

    // END
}
