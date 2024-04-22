package com.example.demo.controller;

import com.example.demo.entity.DataBox;
import com.example.demo.service.DataBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data-boxes")
public class DataBoxController {
    @Autowired
    private DataBoxService DataBoxService;


    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<DataBox>> getAllDataBoxes() {
        return ResponseEntity.ok(DataBoxService.getAllDataBoxes());
    }

    @PostMapping
    public ResponseEntity<DataBox> createDataBox(@RequestBody DataBox dataBox) {
        return ResponseEntity.status(HttpStatus.CREATED).body(DataBoxService.createDataBox(dataBox));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<DataBox> updateDataBox(@PathVariable Long id, @RequestBody DataBox newDataBox) {
//        DataBox existingDataBox = dataBoxRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid data box ID"));
//        newDataBox.setId(existingDataBox.getId());
//        DataBox updatedDataBox = dataBoxRepository.save(newDataBox);
//        return ResponseEntity.ok(updatedDataBox);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDataBox(@PathVariable Long id) {
//        dataBoxRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }

}
