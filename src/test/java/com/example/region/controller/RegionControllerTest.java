package com.example.region.controller;

import com.example.region.dto.RegionDto;
import com.example.region.service.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RegionController.class)
class RegionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegionService regionService;


    @Test
    void shouldGetAllRegions() throws Exception {
        mockMvc.perform(get("/regions"))
                .andExpect(status().isOk());

        verify(regionService, times(1)).getRegionsData();
    }

    @Test
    void shouldGetRegionById() throws Exception {
        mockMvc.perform(get("/regions/5"))
                .andExpect(status().isOk());

        verify(regionService, times(1)).getRegionData(5L);
    }

    @Test
    void shouldAddNewRegion() throws Exception {
        RegionDto region = new RegionDto("Татарстан", "Республика Татарстан");
        String json = new ObjectMapper().writeValueAsString(region);

        mockMvc.perform(post("/regions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(regionService, times(1)).addNewRegion(any(RegionDto.class));
    }

    @Test
    void shouldUpdateRegion() throws Exception {
        RegionDto region = new RegionDto("Татарстан new", "Республика Татарстан new");
        String json = new ObjectMapper().writeValueAsString(region);

        mockMvc.perform(put("/regions/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(regionService, times(1)).updateRegionData(anyLong(), any(RegionDto.class));
    }

    @Test
    void shouldDeleteRegion() throws Exception {
        mockMvc.perform(delete("/regions/5"))
                .andExpect(status().isOk());

        verify(regionService, times(1)).deleteRegion(5L);
    }
}