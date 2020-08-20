package com.github.leosbarai.hamburgueria.repository;

import com.github.leosbarai.hamburgueria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
}
