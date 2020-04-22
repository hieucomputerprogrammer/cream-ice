package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.OrderDetailsDTO;
import io.hieu.creamice.services.IOrderDetailsService;
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
public class OrderDetailsController {
    @Autowired
    private IOrderDetailsService IOrderDetailsService;

//    @RequestMapping(value = "/orderDetails", method = RequestMethod.GET)
//    public ResponseEntity<List<OrderDetailsDTO>> findAllOrderDetails() {
//        List<OrderDetailsDTO> orderDetails = IOrderDetailsService.findAll();
//        if (orderDetails.isEmpty()) {
//            return new ResponseEntity<>(orderDetails, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//    }

    @GetMapping(value = "/orderDetails")
    public Page<OrderDetailsDTO> findAllOrderDetails(Pageable pageable) {
        return IOrderDetailsService.findAll(pageable);
    }

    @GetMapping(value = "/orderDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsDTO> findOrderDetailsById(@PathVariable("id") Long id) {
        Optional<OrderDetailsDTO> orderDetails = IOrderDetailsService.findById(id);
        if (orderDetails.isPresent()) {
            return new ResponseEntity<>(orderDetails.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/orderDetails")
    public ResponseEntity<OrderDetailsDTO> createOrderDetails(@RequestBody OrderDetailsDTO orderDetailsDTO) {
        orderDetailsDTO = IOrderDetailsService.create(orderDetailsDTO);
        return new ResponseEntity<>(orderDetailsDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/orderDetails/{id}")
    public ResponseEntity<OrderDetailsDTO> updateOrderDetails(@PathVariable("id") Long id, @RequestBody OrderDetailsDTO orderDetailsDTO) {
        orderDetailsDTO = IOrderDetailsService.update(orderDetailsDTO, id);
        return new ResponseEntity<>(orderDetailsDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/orderDetails/{id}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable("id") Long id) {
        IOrderDetailsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}