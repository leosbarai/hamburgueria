package com.github.leosbarai.hamburgueria.user.service;

import com.github.leosbarai.hamburgueria.user.dto.UserDTO;
import com.github.leosbarai.hamburgueria.user.entity.User;
import com.github.leosbarai.hamburgueria.user.parser.UserParser;
import com.github.leosbarai.hamburgueria.user.repository.UserJpaRepository;
import com.github.leosbarai.hamburgueria.user.service.exceptions.DataBaseException;
import com.github.leosbarai.hamburgueria.user.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserJpaRepository repository;

    private final UserParser parser;

    @Autowired
    public UserService(UserJpaRepository repository, UserParser parser) {
        this.repository = repository;
        this.parser = parser;
    }

    public List<UserDTO> findAll() {
        List<User> listUser = repository.findAll();
        List<UserDTO> userDTOList = listUser.stream().map(parser::toDTO).collect(Collectors.toList());
        return userDTOList;
    }

    public UserDTO findById(Long id) {
        Optional<User> user = repository.findById(id);
        return parser.toDTO(user.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public User update(Long id, User updateUser) {
        try {
            User user = repository.getOne(id);
            updateDate(user, updateUser);
            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateDate(User user, User updateUser) {
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
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

}
