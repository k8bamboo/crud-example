package com.example.region.service;

import com.example.region.dto.RegionDto;
import com.example.region.repository.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionServiceImpl(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Override
    public List<RegionDto> getRegionsData() {
        return regionMapper.getRegions();
    }

    @Override
    public RegionDto getRegionData(Long id) {
        return regionMapper.getRegion(id);
    }

    @Override
    public void addNewRegion(RegionDto dto) {
        regionMapper.addRegion(dto.getShortName(), dto.getFullName());
    }

    @Override
    public void updateRegionData(Long id, RegionDto dto) {
        regionMapper.updateRegion(id, dto.getShortName(), dto.getFullName());
    }

    @Override
    public void deleteRegion(Long id) {
        regionMapper.deleteRegion(id);
    }
}
