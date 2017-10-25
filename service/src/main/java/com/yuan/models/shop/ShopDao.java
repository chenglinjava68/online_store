package com.yuan.models.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author joryun ON 2017/10/25.
 */

public interface ShopDao extends JpaRepository<Shop, Integer>, JpaSpecificationExecutor<Shop> {
}
