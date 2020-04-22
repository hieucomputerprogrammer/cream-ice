package io.hieu.creamice.repositories;

import io.hieu.creamice.beans.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    void deleteByOrderIdAndRecipeId(Long orderId, Long recipeId);
}