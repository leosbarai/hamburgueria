package com.github.leosbarai.hamburgueria.user.service;

import com.github.leosbarai.hamburgueria.user.entity.User;
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

@Service
public class UserService {

    @Autowired
    UserJpaRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
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
