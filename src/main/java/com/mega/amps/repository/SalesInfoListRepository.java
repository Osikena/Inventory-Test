package com.mega.amps.repository;

import com.mega.amps.domain.SalesInfoConst;
import com.mega.amps.domain.SalesInfoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesInfoListRepository extends JpaRepository <SalesInfoList, Long> {

    @Query("select salesInfoList from SalesInfoList salesInfoList where salesInfoList.sales_id = ?1")
    List<SalesInfoList> findSalesBySalesId(String salesid);

}
