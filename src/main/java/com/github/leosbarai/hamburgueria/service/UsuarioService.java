package com.github.leosbarai.hamburgueria.service;

import com.github.leosbarai.hamburgueria.entity.Usuario;
import com.github.leosbarai.hamburgueria.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioJpaRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new NullPointerException());
    }


}
