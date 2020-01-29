package com.example.region.service;

import com.example.region.dto.RegionDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegionServiceImplTest {

    @Autowired
    private RegionService regionService;

    @Test
    void shouldGetRegionsData() {
        List<RegionDto> regionsData = regionService.getRegionsData();

        assertEquals(2, regionsData.size());

        regionsData.forEach(regionDto -> {
            assertNotNull(regionDto.getId());
            assertNotNull(regionDto.getShortName());
            assertNotNull(regionDto.getFullName());
        });
    }

    @Test
    void shouldGetRegionData() {
        RegionDto regionDto = regionService.getRegionData(1L);

        assertEquals("Алтай", regionDto.getShortName());
        assertEquals("Алтайский край", regionDto.getFullName());
    }

    @Test
    void shouldAddNewRegion() {
        int sizeBeforeSaving = regionService.getRegionsData().size();

        RegionDto regionDto = new RegionDto("Карелия", "Республика карелия");
        regionService.addNewRegion(regionDto);

        List<RegionDto> regionsData = regionService.getRegionsData();
        assertEquals(sizeBeforeSaving + 1, regionsData.size());

        RegionDto dataFromDb = regionsData.stream()
                .max(Comparator.comparing(RegionDto::getId))
                .orElseThrow(NoSuchElementException::new);

        assertEquals(regionDto.getShortName(), dataFromDb.getShortName());
        assertEquals(regionDto.getFullName(), dataFromDb.getFullName());
        assertNotNull(dataFromDb.getId());
    }

    @Test
    void shouldUpdateRegionData() {
        RegionDto regionDto = new RegionDto("Алтай new", "Алтайский край new");
        regionService.updateRegionData(1L, regionDto);

        RegionDto dataFromDb = regionService.getRegionData(1L);
        assertEquals(regionDto.getShortName(), dataFromDb.getShortName());
        assertEquals(regionDto.getFullName(), dataFromDb.getFullName());
    }

    @Test
    void shouldDeleteRegionData() {
        RegionDto dataFromDb = regionService.getRegionData(3L);
        assertNotNull(dataFromDb);

        regionService.deleteRegion(3L);
        RegionDto afterDeleting = regionService.getRegionData(3L);
        assertNull(afterDeleting);
    }
}