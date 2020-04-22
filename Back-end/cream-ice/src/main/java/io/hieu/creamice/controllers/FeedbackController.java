package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.FeedbackDTO;
import io.hieu.creamice.services.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class FeedbackController {
    @Autowired
    private IFeedbackService IFeedbackService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

//    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
//    public ResponseEntity<List<FeedbackDTO>> findAllFeedbacks() {
//        List<FeedbackDTO> feedbacks = IFeedbackService.findAll();
//        if (feedbacks.isEmpty()) {
//            return new ResponseEntity<>(feedbacks, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
//    }

    @GetMapping(value = "/feedbacks")
    public Page<FeedbackDTO> findAllFeedbacks(Pageable pageable) {
        return IFeedbackService.findAll(pageable);
    }

    @GetMapping(value = "/feedbacks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackDTO> findFeedbackById(@PathVariable("id") Long id) {
        Optional<FeedbackDTO> feedback = IFeedbackService.findById(id);
        if (feedback.isPresent()) {
            return new ResponseEntity<>(feedback.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/feedbacks")
    public ResponseEntity<FeedbackDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackDTO = IFeedbackService.create(feedbackDTO);
        return new ResponseEntity<>(feedbackDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/feedbacks/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
        feedbackDTO = IFeedbackService.update(feedbackDTO, id);
        return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/feedbacks/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Long id) {
        IFeedbackService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}