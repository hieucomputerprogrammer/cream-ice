package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.FAQ;
import io.hieu.creamice.dto.FAQDTO;

public class FAQMapper {
    public static FAQDTO faqToFAQDTO(FAQ faq) {
        FAQDTO faqDTO;
        faqDTO = new FAQDTO(faq.getId(), faq.getQuestion(), faq.getAnswer(), faq.getStatus());
        return faqDTO;
    }

    public static FAQ faqDTOToFAQ(FAQDTO faqDTO) {
        FAQ faq = new FAQ();
        faq.setQuestion(faqDTO.getQuestion());
        faq.setAnswer(faqDTO.getAnswer());
        faq.setStatus(faqDTO.getStatus());
        return faq;
    }
}