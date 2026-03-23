package com.sivareddy.service;

import com.sivareddy.model.Customer;
import com.sivareddy.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.Beans;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    public Customer createCustomer(Customer dto){
        Customer data = Customer.builder()
                .customerName(dto.getCustomerName())
                .mobileNum(dto.getMobileNum())
                .emailAdd(dto.getEmailAdd())
                .createdDtTime(LocalDateTime.now())
                .dateOfBirth(dto.getDateOfBirth())
                .address(dto.getAddress())
                .build();
        customerRepo.save(data);
        return data;
    }
    public Customer getCustomer(int id){
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
    }
    public Customer updateCustomer(Customer dto){
        Customer data = getCustomer(dto.getId());
        updateCustomerInfoFromDTO(data,dto);
        customerRepo.save(data);
        return data;
    }

    private void updateCustomerInfoFromDTO(Customer data, Customer dto) {
        BeanUtils.copyProperties(dto,data,"id","createdDtTime");
    }
}
