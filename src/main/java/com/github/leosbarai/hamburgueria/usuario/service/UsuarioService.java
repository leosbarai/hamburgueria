package com.github.leosbarai.hamburgueria.usuario.service;

import com.github.leosbarai.hamburgueria.usuario.entity.Usuario;
import com.github.leosbarai.hamburgueria.usuario.repository.UsuarioJpaRepository;
import com.github.leosbarai.hamburgueria.usuario.service.exceptions.DataBaseException;
import com.github.leosbarai.hamburgueria.usuario.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioJpaRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Usuario insert(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {
        try {
            Usuario usuarioAtual = repository.getOne(id);
            updateDate(usuarioAtual, usuario);
            return repository.save(usuarioAtual);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateDate(Usuario usuarioAtual, Usuario usuario) {
        usuarioAtual.setEmail(usuario.getEmail());
        usuarioAtual.setNome(usuario.getNome());
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
