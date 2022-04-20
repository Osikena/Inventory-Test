package com.mega.amps.repository;

import com.mega.amps.domain.SalesInfoConst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalesInfoConstRepository extends JpaRepository <SalesInfoConst, Long> {

    @Query("select salesInfoConst from SalesInfoConst salesInfoConst where salesInfoConst.sales_id = ?1")
    SalesInfoConst findSalesBySalesId(String salesid);

}
