package com.example.demo.service;


import com.example.demo.entity.DataBox;
import com.example.demo.repository.DataBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataBoxService {
    @Autowired
    private DataBoxRepository dataBoxRepository;

    public void test() {
        System.out.println("DataBoxService test");
    }

    @Transactional(readOnly = true)
    public List<DataBox> getAllDataBoxes() {
        return dataBoxRepository.findAll();
    }

    public DataBox createDataBox(DataBox dataBox) {
        return dataBoxRepository.save(dataBox);
    }

}
