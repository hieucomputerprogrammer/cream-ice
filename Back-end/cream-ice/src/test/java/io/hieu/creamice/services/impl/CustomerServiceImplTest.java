package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Customer;
import io.hieu.creamice.dto.CustomerDTO;
import io.hieu.creamice.repositories.ICustomerRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {
    @Test
    public void find_findAll_customersNotFound() {
        ICustomerRepository customerRepository = mock(ICustomerRepository.class);

        when(customerRepository.findAll()).thenReturn(Collections.emptyList());
        CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl(customerRepository);

        List<CustomerDTO> customers = customerServiceImpl.findAll();
        assertTrue(customers.isEmpty());
    }


    @Test
    public void find_findAll_customersFound() {
        ICustomerRepository customerRepository = mock(ICustomerRepository.class);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());


        when(customerRepository.findAll()).thenReturn(customers);
        CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl(customerRepository);

        List<CustomerDTO> customersList = customerServiceImpl.findAll();
        assertFalse(customersList.isEmpty());
    }

    @Test
    public void find_findCustomerById_customerFound() {
        final Long mockId = 1L;
        ICustomerRepository customerRepository = mock(ICustomerRepository.class);
        Customer customer = new Customer();

        when(customerRepository.findById(mockId)).thenReturn(Optional.of(customer));
        CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl(customerRepository);

        Optional<CustomerDTO> customerDTO = customerServiceImpl.findById(mockId);
        assertTrue(customerDTO.isPresent());
    }

    @Test
    public void find_findCustomerById_customerNotFound() {
        final Long mockId = 1L;
        ICustomerRepository customerRepository = mock(ICustomerRepository.class);

        when(customerRepository.findById(mockId)).thenReturn(Optional.empty());
        CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl(customerRepository);

        Optional<CustomerDTO> userDTO = customerServiceImpl.findById(mockId);
        assertTrue(userDTO.isPresent());
    }
}