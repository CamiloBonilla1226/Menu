package com.unicauca.my_food.infrastucture.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.my_food.application.waste.IWasteService;
import com.unicauca.my_food.domain.waste.Waste;
import com.unicauca.my_food.domain.waste.value_objects.CauseWaste;
import com.unicauca.my_food.domain.waste.value_objects.ProductWaste;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wastes")
public class WasteRestController {
    private final IWasteService service;

    @PostMapping("")
    public ResponseEntity<Waste> createWaste(@RequestBody Waste waste) {
        Waste createdWaste = service.createWaste(waste);
        return new ResponseEntity<Waste>(createdWaste, HttpStatus.CREATED);
    }

    @GetMapping("/{idWaste}")
    public ResponseEntity<Waste> getWasteById(@PathVariable("idWaste") String idWaste) {
        Waste waste = service.getWasteById(idWaste);
        return new ResponseEntity<Waste>(waste, HttpStatus.OK);
    }
    
    @GetMapping("")
    public ResponseEntity<List<Waste>> getAllWaste() {
        List<Waste> wasteList = service.getAllWaste();
        return new ResponseEntity<List<Waste>>(wasteList, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWaste(@PathVariable("id") String wasteId, @RequestBody Waste waste) {
        service.updateWaste(wasteId, waste);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWaste(@PathVariable("id") String wasteId) {
        service.deleteWaste(wasteId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/register/{id}")
    public ResponseEntity<Void> registerWaste(@PathVariable("id") String wasteId, @RequestParam double quantity) {
        service.registerWaste(wasteId, quantity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/suggest/{id}")
    public ResponseEntity<Void> suggestReductionMeasures(@PathVariable("id") String wasteId) {
        service.suggestReductionMeasures(wasteId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/product/details/{id}")
    public ResponseEntity<String> getDetailsProduct(@PathVariable("id") String productId, @RequestBody ProductWaste product) {
        String details = service.getDetailsProduct(product);
        return new ResponseEntity<String>(details, HttpStatus.OK);
    }

    @PostMapping("/cause/select")
    public ResponseEntity<String> selectCause(@RequestBody CauseWaste cause, @RequestParam int causeIndex) {
        String selectedCause = service.selectCause(cause, causeIndex);
        return new ResponseEntity<String>(selectedCause, HttpStatus.OK);
    }

}
