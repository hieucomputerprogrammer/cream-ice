package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.IceCream;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIceCreamRepository extends JpaRepository<IceCream, Long> {
}