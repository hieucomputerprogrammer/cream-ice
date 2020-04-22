package io.hieu.creamice.services;

import io.hieu.creamice.dto.FAQDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IFAQService {
    List<FAQDTO> findAll();

    Optional<FAQDTO> findById(Long id);

    FAQDTO create(FAQDTO faqdto);

    FAQDTO update(FAQDTO faqDTO, Long id);

    Page<FAQDTO> findAll(Pageable pageable);

    void delete(Long id);
}