package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}