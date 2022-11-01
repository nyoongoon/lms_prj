package com.prj.lms.repository.cart;

import com.prj.lms.domain.cart.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
