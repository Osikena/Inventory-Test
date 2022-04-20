package com.mega.amps.mapper;

import com.mega.amps.domain.SalesInfoConst;
import com.mega.amps.dto.SalesInfoConstDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface SalesInfoConstMapper {

//    @Mapping(source = "product_barcode", target = "product_barcode")
    @Mapping(source = "sales_id", target = "sales_id")
    SalesInfoConst toSalesInfoConst(SalesInfoConstDTO salesInfoConstDTO);

    List<SalesInfoConstDTO> allSalesInfoConstDTO(List<SalesInfoConst> salesInfoConsts);

    List<SalesInfoConst> allSalesInfoConstant(List<SalesInfoConstDTO> salesInfoConstDTOS);

    @Mapping(source = "sales_info_const_id", target = "sales_info_const_id")
    SalesInfoConstDTO toSalesInfoConstDTO(SalesInfoConst salesInfoConst);

}
