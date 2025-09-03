package com.inditex.core.price.infrastructure.adapters.out.persistence.repository;

import com.inditex.core.price.infrastructure.adapters.out.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = """
        SELECT p FROM PriceEntity p
        WHERE p.brandId = :brandId
        AND p.productId = :productId
        AND :applicationDate BETWEEN p.startDate AND p.endDate
        AND p.priority = (
            SELECT MAX(p2.priority)
            FROM PriceEntity p2
            WHERE p2.brandId = :brandId
            AND p2.productId = :productId
            AND :applicationDate BETWEEN p2.startDate AND p2.endDate
        )
        """)
    Optional<PriceEntity> findByBrandAndProductAndApplicationDate(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("applicationDate") LocalDateTime applicationDate
    );

}
