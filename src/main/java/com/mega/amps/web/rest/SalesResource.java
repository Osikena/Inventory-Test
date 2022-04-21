package com.mega.amps.web.rest;

import com.mega.amps.domain.Product;
import com.mega.amps.domain.SalesInfoConst;
import com.mega.amps.domain.SalesInfoList;
import com.mega.amps.domain.logic.Logic;
import com.mega.amps.dto.SalesInfoConstDTO;
import com.mega.amps.dto.SalesInfoDTO;
import com.mega.amps.dto.SalesInfoListDTO;
import com.mega.amps.dto.response.GenericResponse;
import com.mega.amps.mapper.SalesInfoConstMapper;
import com.mega.amps.mapper.SalesInfoListMapper;
import com.mega.amps.repository.SalesInfoListRepository;
import com.mega.amps.service.ProductService;
import com.mega.amps.service.SalesInfoConstService;
import com.mega.amps.service.SalesInofListService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesResource {

    private Logic logic;
    private SalesInfoConstService salesInfoConstService;
    private SalesInofListService salesInofListService;
    private SalesInfoConstMapper salesInfoConstMapper;
    private SalesInfoListMapper salesInfoListMapper;
    private ProductService productService;

    public SalesResource(Logic logic, SalesInfoConstService salesInfoConstService, SalesInofListService salesInofListService,
                         SalesInfoConstMapper salesInfoConstMapper, SalesInfoListMapper salesInfoListMapper,
                         ProductService productService) {
        this.logic = logic;
        this.salesInfoConstService = salesInfoConstService;
        this.salesInofListService = salesInofListService;
        this.salesInfoConstMapper = salesInfoConstMapper;
        this.salesInfoListMapper = salesInfoListMapper;
        this.productService = productService;
    }

    @PostMapping("/sales/sell")
    public ResponseEntity<GenericResponse> sellItems(@Valid @RequestBody SalesInfoDTO salesInfoDTO){

        GenericResponse response = new GenericResponse();
        response.setCode("00");
        response.setMessage("Sales Completed");

        String sales_id = logic.generateSalesId();
        int x = 0;

//        SalesInfoConst salesInfoConst;

//        while (x == 0){
//            salesInfoConst = salesInfoConstService.findSalesBySalesId(sales_id);
//            if (salesInfoConst != null){
//                sales_id = logic.generateSalesId();
//            }else {
//                x = 1;
//            }
//        }

        if (salesInfoDTO.getSales_id() == null){
            salesInfoDTO.setSales_id(sales_id);
        }

        String datetime = salesInfoDTO.getDate_time_created();
        LocalDateTime date_time;
        if (datetime == null){
            date_time = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            datetime = date_time.format(dateTimeFormatter);
            salesInfoDTO.setDate_time_created(datetime);
        }

        SalesInfoConstDTO salesInfoConstDTO = new SalesInfoConstDTO();
        salesInfoConstDTO.setSales_id(salesInfoDTO.getSales_id());
        salesInfoConstDTO.setCustomer_name(salesInfoDTO.getCustomer_name());
        salesInfoConstDTO.setSeller_username(salesInfoDTO.getSeller_username());
        salesInfoConstDTO.setGrand_total_price(salesInfoDTO.getGrand_total_price());
        salesInfoConstDTO.setGrand_total_cost_price(salesInfoDTO.getGrand_total_cost_price());
        salesInfoConstDTO.setGrand_total_profit_loss(salesInfoDTO.getGrand_total_profit_loss());
        salesInfoConstDTO.setMode_of_payment(salesInfoDTO.getMode_of_payment());
        salesInfoConstDTO.setDate_time_created(salesInfoDTO.getDate_time_created());

        List<SalesInfoListDTO> salesInfoListDTOList = salesInfoDTO.getSalesList();

        SalesInfoConst salesInfoConst = salesInfoConstMapper.toSalesInfoConst(salesInfoConstDTO);

        List<SalesInfoList> salesInfoLists = salesInfoListMapper.allSalesInfoList(salesInfoListDTOList);

        salesInfoConstService.save(salesInfoConst);

        for (SalesInfoList salesInfoList : salesInfoLists){

            Product product = productService.findProductByBarcode(salesInfoList.getProduct_barcode());
            product.setQuantity(Long.toString(Long.parseLong(product.getQuantity()) - Long.parseLong(salesInfoList.getSelling_quantity())));

            productService.save(product);

            salesInfoList.setSales_id(sales_id);
            salesInofListService.save(salesInfoList);
        }

        salesInfoConst = salesInfoConstService.findSalesBySalesId(sales_id);

        salesInfoConstDTO = salesInfoConstMapper.toSalesInfoConstDTO(salesInfoConst);

        salesInfoDTO.setSales_info_const_id(salesInfoConstDTO.getSales_info_const_id());
        salesInfoDTO.setSales_id(salesInfoConstDTO.getSales_id());
        salesInfoDTO.setCustomer_name(salesInfoConstDTO.getCustomer_name());
        salesInfoDTO.setSeller_username(salesInfoConstDTO.getSeller_username());
        salesInfoDTO.setGrand_total_price(salesInfoConstDTO.getGrand_total_price());
        salesInfoDTO.setGrand_total_cost_price(salesInfoConstDTO.getGrand_total_cost_price());
        salesInfoDTO.setGrand_total_profit_loss(salesInfoConstDTO.getGrand_total_profit_loss());
        salesInfoDTO.setMode_of_payment(salesInfoConstDTO.getMode_of_payment());
        salesInfoDTO.setDate_time_created(salesInfoConstDTO.getDate_time_created());

        List<SalesInfoList> salesInfoList = salesInofListService.findSalesBySalesId(sales_id);

        salesInfoListDTOList = salesInfoListMapper.allSalesInfoListDTO(salesInfoList);

        salesInfoDTO.setSalesList(salesInfoListDTOList);

        response.setData(salesInfoDTO);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/sales/{fromdate}/{todate}/{modeofpayment}")
    public ResponseEntity<GenericResponse> listofsales(@Valid @PathVariable String fromdate, String todate, String modeofpayment){

        GenericResponse response = new GenericResponse();

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);

    }

}
