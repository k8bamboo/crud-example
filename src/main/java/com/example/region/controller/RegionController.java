package com.example.region.controller;

import com.example.region.dto.RegionDto;
import com.example.region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/regions")
    public List<RegionDto> getAllRegions() {
        return regionService.getRegionsData();
    }

    @GetMapping("/regions/{id}")
    public RegionDto getRegionById(@PathVariable("id") Long id) {
        return regionService.getRegionData(id);
    }

    @PostMapping("/regions")
    public void addNewRegion(@RequestBody RegionDto dto) {
        regionService.addNewRegion(dto);
    }

    @PutMapping("/regions/{id}")
    public void updateRegion(@PathVariable("id") Long id,
                             @RequestBody RegionDto dto) {
        regionService.updateRegionData(id, dto);
    }

    @DeleteMapping("/regions/{id}")
    public void deleteRegion(@PathVariable("id") Long id) {
        regionService.deleteRegion(id);
    }
}
