package com.example.region.repository;

import com.example.region.dto.RegionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("SELECT id, short_name shortName, full_name fullName FROM REGION")
    List<RegionDto> getRegions();

    @Select("SELECT id, short_name shortName, full_name fullName FROM REGION WHERE id = #{id}")
    RegionDto getRegion(@Param("id") Long id);

    @Select("INSERT INTO REGION (short_name, full_name) VALUES (#{shortName}, #{fullName})")
    void addRegion(@Param("shortName") String shortName,
                        @Param("fullName") String fullName);

    @Select("UPDATE REGION SET id = #{id}, short_name = #{shortName}, full_name = #{fullName} WHERE id = #{id}")
    void updateRegion(@Param("id") Long id,
                           @Param("shortName") String shortName,
                           @Param("fullName") String fullName);

    @Select("DELETE REGION WHERE id = #{id}")
    void deleteRegion(@Param("id") Long id);

}
