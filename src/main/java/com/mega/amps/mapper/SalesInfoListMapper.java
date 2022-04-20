package com.mega.amps.mapper;

import com.mega.amps.domain.SalesInfoList;
import com.mega.amps.dto.SalesInfoListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface SalesInfoListMapper {

//    @Mapping(source = "product_barcode", target = "product_barcode")
    @Mapping(source = "sales_id", target = "sales_id")
    SalesInfoList toSalesInfoList(SalesInfoListDTO salesInfoListDTO);

    List<SalesInfoListDTO> allSalesInfoListDTO(List<SalesInfoList> salesInfoLists);

    List<SalesInfoList> allSalesInfoList(List<SalesInfoListDTO> salesInfoListDTOList);

    @Mapping(source = "sales_info_list_id", target = "sales_info_list_id")
    SalesInfoListDTO toSalesInfoListDTO(SalesInfoList salesInfoList);

}
