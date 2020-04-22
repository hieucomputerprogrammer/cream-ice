package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.FAQ;
import io.hieu.creamice.dto.FAQDTO;
import io.hieu.creamice.modelmappers.FAQMapper;
import io.hieu.creamice.repositories.IFAQRepository;
import io.hieu.creamice.services.IFAQService;
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
public class FAQServiceImpl implements IFAQService {
    @Autowired
    private IFAQRepository IFAQRepository;

    @Autowired
    public FAQServiceImpl(IFAQRepository IFAQRepository) {
        this.IFAQRepository = IFAQRepository;
    }

    @Override
    public List<FAQDTO> findAll() {
        List<FAQ> faqs = IFAQRepository.findAll();
        List<FAQDTO> faqDTOs = new ArrayList<FAQDTO>();
        for (FAQ faq : faqs) {
            faqDTOs.add(FAQMapper.faqToFAQDTO(faq));
        }
        return faqDTOs;
    }

    @Override
    public Page<FAQDTO> findAll(Pageable pageable) {
        Page<FAQ> faqsPage = IFAQRepository.findAll(pageable);
        Page<FAQDTO> faqDTOsPage = faqsPage.map(faq -> FAQMapper.faqToFAQDTO(faq));
        return faqDTOsPage;
    }

    @Override
    public Optional<FAQDTO> findById(Long id) {
        return IFAQRepository.findById(id)
                .map(faq -> FAQMapper.faqToFAQDTO(faq));
    }

    @Override
    public FAQDTO create(FAQDTO faqDTO) {
        FAQ faq = FAQMapper.faqDTOToFAQ(faqDTO);
        faq = IFAQRepository.save(faq);
        return FAQMapper.faqToFAQDTO(faq);
    }

    @Override
    public FAQDTO update(FAQDTO faqDTO, Long id) {
        Optional<FAQ> currentFAQ = IFAQRepository.findById(id);

        if (!currentFAQ.isPresent()) {
            throw new RuntimeException("No FAQ ID " + id + " found!");
        }
        FAQ faq = currentFAQ.get();
        faq.setQuestion(faqDTO.getQuestion());
        faq.setAnswer(faqDTO.getAnswer());
        faq.setStatus(faqDTO.getStatus());

        return FAQMapper.faqToFAQDTO(faq);
    }

    @Override
    public void delete(Long id) {
        IFAQRepository.deleteById(id);
    }
}