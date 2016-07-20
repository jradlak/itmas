package com.jrd.itmas_server.repository;

import com.jrd.itmas_server.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jakub on 13.04.16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    Optional<User> findOneById(Long userId);

    @Override
    void delete(User t);
}
