package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.CustomerDTO;
import io.hieu.creamice.dto.CustomerInputDTO;
import io.hieu.creamice.services.ICustomerService;
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

@CrossOrigin(
        origins = "http://127.0.0.1:4200",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE
        }
)
@RestController
public class CustomerController {
    @Autowired
    private ICustomerService ICustomerService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

//    @RequestMapping(value = "/customers", method = RequestMethod.GET)
//    public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
//        List<CustomerDTO> customers = ICustomerService.findAll();
//        if (customers.isEmpty()) {
//            return new ResponseEntity<>(customers, HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(customers, HttpStatus.OK);
//    }

    @GetMapping(value = "/customers")
    public Page<CustomerDTO> findAllCustomers(Pageable pageable) {
        return ICustomerService.findAll(pageable);
    }

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> findCustomerById(@PathVariable("id") Long id) {
        Optional<CustomerDTO> customer = ICustomerService.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerInputDTO customerInputDTO) {
        CustomerDTO customerDTO = ICustomerService.create(customerInputDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerInputDTO customerInputDTO) {
        CustomerDTO customerDTO = ICustomerService.update(customerInputDTO, id);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        ICustomerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}