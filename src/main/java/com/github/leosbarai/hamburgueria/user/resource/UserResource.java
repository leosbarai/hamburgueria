package com.github.leosbarai.hamburgueria.user.resource;

import com.github.leosbarai.hamburgueria.user.entity.User;
import com.github.leosbarai.hamburgueria.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserService service;

    @GetMapping
    public List<User> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(service.findById(id));
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable Long id, @RequestBody User user) {
        user = service.update(id, user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}