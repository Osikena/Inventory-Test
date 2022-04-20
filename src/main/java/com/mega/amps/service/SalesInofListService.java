package com.mega.amps.service;

import com.mega.amps.domain.SalesInfoList;
import com.mega.amps.repository.SalesInfoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesInofListService {

    SalesInfoListRepository salesInfoListRepository;

    public SalesInofListService(SalesInfoListRepository salesInfoListRepository) {
        this.salesInfoListRepository = salesInfoListRepository;
    }

    public Optional<SalesInfoList> findById(Long id){
        return salesInfoListRepository.findById(id);
    }

    public SalesInfoList getById(Long id) { return  salesInfoListRepository.getById(id); }

    public List<SalesInfoList> findAll(){
        return salesInfoListRepository.findAll();
    }

    public SalesInfoList save(SalesInfoList salesInfoList){
        return salesInfoListRepository.save(salesInfoList);
    }

    public SalesInfoList saveAndFlush(SalesInfoList salesInfoList){
        return salesInfoListRepository.saveAndFlush(salesInfoList);
    }

    public void deleteById(Long id){
        salesInfoListRepository.deleteById(id);
    }

    public List<SalesInfoList> findSalesBySalesId(String salesid) { return salesInfoListRepository.findSalesBySalesId(salesid); }

}
