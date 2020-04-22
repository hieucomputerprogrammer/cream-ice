package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}