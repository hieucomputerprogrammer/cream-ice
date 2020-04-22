package io.hieu.creamice.services;

import io.hieu.creamice.dto.FeedbackDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    List<FeedbackDTO> findAll();

    Optional<FeedbackDTO> findById(Long id);

    FeedbackDTO create(FeedbackDTO feedbackDTO);

    FeedbackDTO update(FeedbackDTO feedbackDTO, Long id);

    Page<FeedbackDTO> findAll(Pageable pageable);

    void delete(Long id);
}