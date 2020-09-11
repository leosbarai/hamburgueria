package com.github.leosbarai.hamburgueria.user.service;

import com.github.leosbarai.hamburgueria.config.PasswordCheck;
import com.github.leosbarai.hamburgueria.user.dto.UserDTO;
import com.github.leosbarai.hamburgueria.user.entity.User;
import com.github.leosbarai.hamburgueria.user.parser.UserParser;
import com.github.leosbarai.hamburgueria.user.repository.UserJpaRepository;
import com.github.leosbarai.hamburgueria.user.service.exceptions.DataBaseException;
import com.github.leosbarai.hamburgueria.user.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserJpaRepository repository;

    private final UserParser parser;

    private final PasswordEncoder passwordEncoder;

    private final PasswordCheck check;

    @Autowired
    public UserService(UserJpaRepository repository, UserParser parser, PasswordEncoder passwordEncoder, PasswordCheck check) {
        this.repository = repository;
        this.parser = parser;
        this.passwordEncoder = passwordEncoder;
        this.check = check;
    }

    public List<UserDTO> findAll() {
        List<User> listUser = repository.findAll();
        return listUser.stream().map(parser::toDTO).collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        Optional<User> user = repository.findById(id);
        return parser.toDTO(user.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public User insert(UserDTO userDTO) throws Exception {
        check.checkPasswordRules(userDTO.password);
        User user = createUser(userDTO);
        return repository.save(user);
    }

    public User update(Long id, UserDTO updateUser) {
        try {
            User user = repository.getOne(id);
            updateDate(user, updateUser);
            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateDate(User user, UserDTO updateUser) {
        user.setEmail(updateUser.email);
        user.setName(updateUser.name);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private User createUser(UserDTO userDTO) {
        userDTO.password = passwordEncoder.encode(userDTO.password);
        User user = new User();
        user.setName(userDTO.name);
        user.setEmail(userDTO.email);
        user.setPassword(userDTO.password);
        return user;
    }


}
