package com.example.region.service;

import com.example.region.dto.RegionDto;

import java.util.List;

public interface RegionService {
    List<RegionDto> getRegionsData();

    RegionDto getRegionData(Long id);

    void addNewRegion(RegionDto dto);

    void updateRegionData(Long id, RegionDto dto);

    void deleteRegion(Long id);
}
