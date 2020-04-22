package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.PaymentDTO;
import io.hieu.creamice.services.IPaymentService;
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
public class PaymentController {
    @Autowired
    private IPaymentService IPaymentService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

//    @RequestMapping(value = "/payments", method = RequestMethod.GET)
//    public ResponseEntity<List<PaymentDTO>> findAllPayments() {
//        List<PaymentDTO> payments = IPaymentService.findAll();
//        if (payments.isEmpty()) {
//            return new ResponseEntity<>(payments, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(payments, HttpStatus.OK);
//    }

    @GetMapping(value = "/payments")
    public Page<PaymentDTO> findAllPayments(Pageable pageable) {
        return IPaymentService.findAll(pageable);
    }

    @GetMapping(value = "/payments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDTO> findPaymentById(@PathVariable("id") Long id) {
        Optional<PaymentDTO> payment = IPaymentService.findById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/payments")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        paymentDTO = IPaymentService.create(paymentDTO);
        return new ResponseEntity<>(paymentDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/payments/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable("id") Long id, @RequestBody PaymentDTO paymentDTO) {
        paymentDTO = IPaymentService.update(paymentDTO, id);
        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable("id") Long id) {
        IPaymentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}