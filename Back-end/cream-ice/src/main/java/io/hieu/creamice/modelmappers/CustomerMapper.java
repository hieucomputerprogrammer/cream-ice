package io.hieu.creamice.modelmappers;

import io.hieu.creamice.beans.Customer;
import io.hieu.creamice.dto.CustomerDTO;
import io.hieu.creamice.dto.CustomerInputDTO;

public class CustomerMapper {
    public static CustomerDTO customerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO;
        customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(), customer.getDate_of_birth()
                , customer.getAddress(), customer.getGender(), customer.getAvatar(), customer.getStatus(), customer.getNum_of_login_failed());
        return customerDTO;
    }

    public static Customer customerInputDTOToCustomer(CustomerInputDTO customerInputDTO) {
        Customer customer = new Customer();
        customer.setName(customerInputDTO.getName());
        customer.setPassword(customerInputDTO.getPassword());
        customer.setEmail(customerInputDTO.getEmail());
        customer.setPhone(customerInputDTO.getPhone());
        customer.setDate_of_birth(customerInputDTO.getDateOfBirth());
        customer.setAddress(customerInputDTO.getAddress());
        customer.setGender(customerInputDTO.getGender());
        customer.setAvatar(customerInputDTO.getAvatar());
        customer.setStatus(customerInputDTO.getStatus());
        return customer;
    }
}