package com.github.leosbarai.hamburgueria.resource.usuario;

import com.github.leosbarai.hamburgueria.entity.Usuario;
import com.github.leosbarai.hamburgueria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    UsuarioService service;

    @GetMapping
    public List<Usuario> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = Optional.ofNullable(service.findById(id));
        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }


}
