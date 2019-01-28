package com.bonc.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author TanRW
 * @create 2019/1/26
 * @since 1.0.0
 */
@Mapper
public interface DicMapper {

    List<String> getAllParentCode(@Param("typeCode") String[] typeCode);

    List<Map<String, Object>> getActTypeByParentCode(String parentCode);
}