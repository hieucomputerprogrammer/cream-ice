package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.Customer;
import io.hieu.creamice.beans.Feedback;
import io.hieu.creamice.beans.Order;
import io.hieu.creamice.dto.FeedbackDTO;

public class FeedbackMapper {
    public static FeedbackDTO feedbackToFeedbackDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO;
        feedbackDTO = new FeedbackDTO(feedback.getId(), feedback.getCustomer().getId(), feedback.getOrder().getId(),
                feedback.getDetail(), feedback.getCreate_date());
        return feedbackDTO;
    }

    public static Feedback feedbackDTOToFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setCustomer(new Customer(feedbackDTO.getCustomerID()));
        feedback.setOrder(new Order(feedbackDTO.getCustomerID()));
        feedback.setDetail(feedbackDTO.getDetails());
        feedback.setCreate_date(feedbackDTO.getCreateDate());
        return feedback;
    }
}