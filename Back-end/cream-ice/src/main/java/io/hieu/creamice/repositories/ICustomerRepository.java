package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}