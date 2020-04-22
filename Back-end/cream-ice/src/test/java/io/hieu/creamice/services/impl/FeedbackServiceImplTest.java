package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Feedback;
import io.hieu.creamice.dto.FeedbackDTO;
import io.hieu.creamice.repositories.IFeedbackRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeedbackServiceImplTest {
    @Test
    public void find_findAll_feedbacksNotFound() {
        IFeedbackRepository feedbackRepository = mock(IFeedbackRepository.class);

        when(feedbackRepository.findAll()).thenReturn(Collections.emptyList());
        FeedbackServiceImpl feedbackServiceImpl = new FeedbackServiceImpl(feedbackRepository);

        List<FeedbackDTO> feedbacks = feedbackServiceImpl.findAll();
        assertTrue(feedbacks.isEmpty());
    }


    @Test
    public void find_findAll_feedbacksFound() {
        IFeedbackRepository feedbackRepository = mock(IFeedbackRepository.class);
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(new Feedback());


        when(feedbackRepository.findAll()).thenReturn(feedbacks);
        FeedbackServiceImpl feedbackServiceImpl = new FeedbackServiceImpl(feedbackRepository);

        List<FeedbackDTO> feedbacksList = feedbackServiceImpl.findAll();
        assertFalse(feedbacksList.isEmpty());
    }

    @Test
    public void find_findFeedbackById_feedbackFound() {
        final Long mockId = 1L;
        IFeedbackRepository feedbackRepository = mock(IFeedbackRepository.class);
        Feedback feedback = new Feedback();

        when(feedbackRepository.findById(mockId)).thenReturn(Optional.of(feedback));
        FeedbackServiceImpl feedbackServiceImpl = new FeedbackServiceImpl(feedbackRepository);

        Optional<FeedbackDTO> feedbackDTO = feedbackServiceImpl.findById(mockId);
        assertTrue(feedbackDTO.isPresent());
    }

    @Test
    public void find_findFeedbackById_feedbackNotFound() {
        final Long mockId = 1L;
        IFeedbackRepository feedbackRepository = mock(IFeedbackRepository.class);

        when(feedbackRepository.findById(mockId)).thenReturn(Optional.empty());
        FeedbackServiceImpl feedbackServiceImpl = new FeedbackServiceImpl(feedbackRepository);

        Optional<FeedbackDTO> feedbackDTO = feedbackServiceImpl.findById(mockId);
        assertTrue(feedbackDTO.isPresent());
    }
}