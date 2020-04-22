package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.FAQDTO;
import io.hieu.creamice.services.IFAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class FAQController {
    @Autowired
    private IFAQService IFAQService;

//    @RequestMapping(value = "/faqs", method = RequestMethod.GET)
//    public ResponseEntity<List<FAQDTO>> findAllFAQs() {
//        List<FAQDTO> faqs = IFAQService.findAll();
//        if (faqs.isEmpty()) {
//            return new ResponseEntity<>(faqs, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(faqs, HttpStatus.OK);
//    }

    @GetMapping(value = "/faqs")
    public Page<FAQDTO> findAllFAQs(Pageable pageable) {
        return IFAQService.findAll(pageable);
    }

    @GetMapping(value = "/faqs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FAQDTO> findFAQById(@PathVariable("id") Long id) {
        Optional<FAQDTO> faq = IFAQService.findById(id);
        if (faq.isPresent()) {
            return new ResponseEntity<>(faq.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/faqs")
    public ResponseEntity<FAQDTO> createFAQ(@RequestBody FAQDTO faqDTO) {
        faqDTO = IFAQService.create(faqDTO);
        return new ResponseEntity<>(faqDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/faqs/{id}")
    public ResponseEntity<FAQDTO> updateFAQ(@PathVariable("id") Long id, @RequestBody FAQDTO faqDTO) {
        faqDTO = IFAQService.update(faqDTO, id);
        return new ResponseEntity<>(faqDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/faqs/{id}")
    public ResponseEntity<Void> deleteFAQ(@PathVariable("id") Long id) {
        IFAQService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}