package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.OrderDTO;
import io.hieu.creamice.services.IOrderService;
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
public class OrderController {
    @Autowired
    private IOrderService IOrderService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

//    @RequestMapping(value = "/orders", method = RequestMethod.GET)
//    public ResponseEntity<List<OrderDTO>> findAllOrders() {
//        List<OrderDTO> orders = IOrderService.findAll();
//        if (orders.isEmpty()) {
//            return new ResponseEntity<>(orders, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }

    @GetMapping(value = "/orders")
    public Page<OrderDTO> findAllOrders(Pageable pageable) {
        return IOrderService.findAll(pageable);
    }

    @GetMapping(value = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable("id") Long id) {
        Optional<OrderDTO> order = IOrderService.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        orderDTO = IOrderService.create(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO) {
        orderDTO = IOrderService.update(orderDTO, id);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        IOrderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}