package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.FAQ;
import io.hieu.creamice.dto.FAQDTO;
import io.hieu.creamice.repositories.IFAQRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FAQServiceImplTest {
    @Test
    public void find_findAll_faqsNotFound() {
        IFAQRepository faqRepository = mock(IFAQRepository.class);

        when(faqRepository.findAll()).thenReturn(Collections.emptyList());
        FAQServiceImpl faqServiceImpl = new FAQServiceImpl(faqRepository);

        List<FAQDTO> faqs = faqServiceImpl.findAll();
        assertTrue(faqs.isEmpty());
    }


    @Test
    public void find_findAll_faqsFound() {
        IFAQRepository faqRepository = mock(IFAQRepository.class);
        List<FAQ> faqs = new ArrayList<>();
        faqs.add(new FAQ());


        when(faqRepository.findAll()).thenReturn(faqs);
        FAQServiceImpl faqServiceImpl = new FAQServiceImpl(faqRepository);

        List<FAQDTO> faqsList = faqServiceImpl.findAll();
        assertFalse(faqsList.isEmpty());
    }

    @Test
    public void find_findFAQById_faqFound() {
        final Long mockId = 1L;
        IFAQRepository faqRepository = mock(IFAQRepository.class);
        FAQ faq = new FAQ();

        when(faqRepository.findById(mockId)).thenReturn(Optional.of(faq));
        FAQServiceImpl faqServiceImpl = new FAQServiceImpl(faqRepository);

        Optional<FAQDTO> faqDTO = faqServiceImpl.findById(mockId);
        assertTrue(faqDTO.isPresent());
    }

    @Test
    public void find_findFAQById_faqNotFound() {
        final Long mockId = 1L;
        IFAQRepository faqRepository = mock(IFAQRepository.class);

        when(faqRepository.findById(mockId)).thenReturn(Optional.empty());
        FAQServiceImpl faqServiceImpl = new FAQServiceImpl(faqRepository);

        Optional<FAQDTO> faqDTO = faqServiceImpl.findById(mockId);
        assertTrue(faqDTO.isPresent());
    }
}