package com.example.demo.service;


import com.example.demo.aspect.DataSource;
import com.example.demo.entity.DataBox;
import com.example.demo.repository.DataBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource
public class DataBoxService {
    @Autowired
    private DataBoxRepository dataBoxRepository;

    public void test() {
        System.out.println("DataBoxService test");
    }

    public List<DataBox> getAllDataBoxes() {
        return dataBoxRepository.findAll();
    }

    public DataBox createDataBox(DataBox dataBox) {
        return dataBoxRepository.save(dataBox);
    }

}
