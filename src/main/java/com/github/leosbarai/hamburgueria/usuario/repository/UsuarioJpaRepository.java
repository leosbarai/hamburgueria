package com.github.leosbarai.hamburgueria.usuario.repository;

import com.github.leosbarai.hamburgueria.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
}
