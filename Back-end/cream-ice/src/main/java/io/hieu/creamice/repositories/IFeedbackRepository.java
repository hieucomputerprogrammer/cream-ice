package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
}