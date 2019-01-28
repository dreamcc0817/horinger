package com.bonc.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 〈〉
 *
 * @author TanRW
 * @create 2019/1/25
 * @since 1.0.0
 */
@Mapper
public interface FileMapper {

    void save(Map<String, Object> map);

    void updateByActId(@Param("file_use_for") String file_use_for, @Param("act_id") String act_id, @Param("fileIds") String[] fileIds);

    void cancelByActId(@Param("file_use_for") String file_use_for,@Param("act_id") String act_id);

    Map<String, String> findById(String id);

    List<Map<String, Object>> getFileList(@Param("act_id") String act_id);

    List<Map<String, Object>> getFileListByUseFor(@Param("act_id") String id, @Param("mark") String mark);
}