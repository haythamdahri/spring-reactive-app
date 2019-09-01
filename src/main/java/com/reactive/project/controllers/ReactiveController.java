package com.reactive.project.controllers;

import com.reactive.project.entities.User;
import com.reactive.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.Query;

@RestController
public class ReactiveController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Mono<User> saveUser(@RequestBody User user) {
        // Save user
        Mono<User> finalUser = this.userRepository.save(user);
        // Return final user
        return finalUser;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> home() {
        return this.userRepository.findAll();
    }

}
