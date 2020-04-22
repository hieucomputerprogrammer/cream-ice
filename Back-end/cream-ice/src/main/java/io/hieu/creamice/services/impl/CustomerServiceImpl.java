package io.hieu.creamice.services.impl;

import io.hieu.creamice.beans.Customer;
import io.hieu.creamice.dto.CustomerDTO;
import io.hieu.creamice.dto.CustomerInputDTO;
import io.hieu.creamice.modelmappers.CustomerMapper;
import io.hieu.creamice.repositories.ICustomerRepository;
import io.hieu.creamice.services.ICustomerService;
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
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository ICustomerRepository;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository ICustomerRepository) {
        this.ICustomerRepository = ICustomerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = ICustomerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
        for (Customer customer : customers) {
            customerDTOs.add(CustomerMapper.customerToCustomerDTO(customer));
        }
        return customerDTOs;
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<Customer> customersPage = ICustomerRepository.findAll(pageable);
        Page<CustomerDTO> customerDTOsPage = customersPage.map(customer -> CustomerMapper.customerToCustomerDTO(customer));
        return customerDTOsPage;
    }

    @Override
    public Optional<CustomerDTO> findById(Long id) {
        return ICustomerRepository.findById(id)
                .map(customer -> CustomerMapper.customerToCustomerDTO(customer));
    }

    @Override
    public CustomerDTO create(CustomerInputDTO customerInputDTO) {
        Customer customer = CustomerMapper.customerInputDTOToCustomer(customerInputDTO);
        customer = ICustomerRepository.save(customer);
        return CustomerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO update(CustomerInputDTO customerInputDTO, Long id) {
        Optional<Customer> currentCustomer = ICustomerRepository.findById(id);

        if (!currentCustomer.isPresent()) {
            throw new RuntimeException("No customer ID " + id + " found!");
        }
        Customer customer = currentCustomer.get();
        customer.setName(customerInputDTO.getName());
        customer.setEmail(customerInputDTO.getEmail());
        customer.setPhone(customerInputDTO.getPhone());
        customer.setPassword(customerInputDTO.getPassword());
        customer.setDate_of_birth(customerInputDTO.getDateOfBirth());
        customer.setAddress(customerInputDTO.getAddress());
        customer.setGender(customerInputDTO.getGender());
        customer.setAvatar(customerInputDTO.getAvatar());
        customer.setStatus(customerInputDTO.getStatus());

        return CustomerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public void delete(Long id) {
        ICustomerRepository.deleteById(id);
    }
}