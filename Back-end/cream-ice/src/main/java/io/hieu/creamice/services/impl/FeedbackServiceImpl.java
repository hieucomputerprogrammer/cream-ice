package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Customer;
import io.hieu.creamice.beans.Feedback;
import io.hieu.creamice.beans.Order;
import io.hieu.creamice.dto.FeedbackDTO;
import io.hieu.creamice.modelmappers.FeedbackMapper;
import io.hieu.creamice.repositories.IFeedbackRepository;
import io.hieu.creamice.services.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    private IFeedbackRepository IFeedbackRepository;

    @Autowired
    public FeedbackServiceImpl(IFeedbackRepository IFeedbackRepository) {
        this.IFeedbackRepository = IFeedbackRepository;
    }

    @Override
    public List<FeedbackDTO> findAll() {
        List<Feedback> feedbacks = IFeedbackRepository.findAll();
        List<FeedbackDTO> feedbackDTOs = new ArrayList<FeedbackDTO>();
        for (Feedback feedback : feedbacks) {
            feedbackDTOs.add(FeedbackMapper.feedbackToFeedbackDTO(feedback));
        }
        return feedbackDTOs;
    }

    @Override
    public Page<FeedbackDTO> findAll(Pageable pageable) {
        Page<Feedback> feedbacksPage = IFeedbackRepository.findAll(pageable);
        Page<FeedbackDTO> feedbackDTOsPage = feedbacksPage.map(feedback -> FeedbackMapper.feedbackToFeedbackDTO(feedback));
        return feedbackDTOsPage;
    }

    @Override
    public Optional<FeedbackDTO> findById(Long id) {
        return IFeedbackRepository.findById(id)
                .map(feedback -> FeedbackMapper.feedbackToFeedbackDTO(feedback));
    }

    @Override
    public FeedbackDTO create(FeedbackDTO feedbackDTO) {
        Feedback feedback = FeedbackMapper.feedbackDTOToFeedback(feedbackDTO);
        feedback = IFeedbackRepository.save(feedback);
        return FeedbackMapper.feedbackToFeedbackDTO(feedback);
    }

    @Override
    public FeedbackDTO update(FeedbackDTO feedbackDTO, Long id) {
        Optional<Feedback> currentFeedback = IFeedbackRepository.findById(id);

        if (!currentFeedback.isPresent()) {
            throw new RuntimeException("No feedback ID " + id + " found!");
        }
        Feedback feedback = currentFeedback.get();
        feedback.setCustomer(new Customer(feedbackDTO.getCustomerID()));
        feedback.setOrder(new Order(feedbackDTO.getOrderId()));
        feedback.setDetail(feedbackDTO.getDetails());
        feedback.setCreate_date(feedbackDTO.getCreateDate());

        return FeedbackMapper.feedbackToFeedbackDTO(feedback);
    }

    @Override
    public void delete(Long id) {
        IFeedbackRepository.deleteById(id);
    }
}