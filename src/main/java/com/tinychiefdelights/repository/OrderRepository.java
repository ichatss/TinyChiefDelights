package com.tinychiefdelights.repository;

import com.tinychiefdelights.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> { // Интерфейс для репозитория Order

    // Берем ORDER через ID
    Order getById(Long id);

    // Берем блюдо из базы по логике (Ключ - Значение)
    Order getOrderByIdAndCustomerId(Long id, Long id2);

    // Проверка по Id + CustId + OrderStatus
    boolean existsOrderByIdAndCustomerIdAndOrderStatus(Long ordId, Long custId, Boolean status);

    // Проверка по Id + custId + status
    boolean existsOrderByIdAndCustomerIdAndOrderStatusAndReviewIsNull(Long ordId, Long custId, Boolean status);

}