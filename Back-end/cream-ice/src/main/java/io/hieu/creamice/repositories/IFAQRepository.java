package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFAQRepository extends JpaRepository<FAQ, Long> {
}