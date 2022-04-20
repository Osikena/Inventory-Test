package com.mega.amps.service;

import com.mega.amps.domain.SalesInfoConst;
import com.mega.amps.repository.SalesInfoConstRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesInfoConstService {

    private SalesInfoConstRepository salesInfoConstRepository;

    public SalesInfoConstService(SalesInfoConstRepository salesInfoConstRepository) {
        this.salesInfoConstRepository = salesInfoConstRepository;
    }

    public Optional<SalesInfoConst> findById(Long id){
        return salesInfoConstRepository.findById(id);
    }

    public SalesInfoConst getById(Long id) { return  salesInfoConstRepository.getById(id); }

    public List<SalesInfoConst> findAll(){
        return salesInfoConstRepository.findAll();
    }

    public SalesInfoConst save(SalesInfoConst salesInfoConst){
        return salesInfoConstRepository.save(salesInfoConst);
    }

    public SalesInfoConst saveAndFlush(SalesInfoConst salesInfoConst){
        return salesInfoConstRepository.saveAndFlush(salesInfoConst);
    }

    public void deleteById(Long id){
        salesInfoConstRepository.deleteById(id);
    }

    public SalesInfoConst findSalesBySalesId(String salesid) { return salesInfoConstRepository.findSalesBySalesId(salesid); }

}
