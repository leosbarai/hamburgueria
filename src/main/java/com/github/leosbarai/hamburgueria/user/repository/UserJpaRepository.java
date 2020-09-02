package com.github.leosbarai.hamburgueria.user.repository;

import com.github.leosbarai.hamburgueria.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
