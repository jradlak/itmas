package com.jrd.itmas_server.repository;

import com.jrd.itmas_server.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jakub on 24.04.16.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
