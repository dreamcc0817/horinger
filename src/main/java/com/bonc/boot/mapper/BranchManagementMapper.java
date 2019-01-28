package com.bonc.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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
public interface BranchManagementMapper  {

    List<Map<String, Object>> selectActivity(HashMap<String, Object> map);

    void saveActivity(Map<String, Object> map);

    void updateActivity(Map<String, Object> map);

    void updateSummarize(Map<String, Object> map);

    Map<String, Object> getActivityById(@Param("id") String id);

    void pubActivity(@Param("ids") String[] ids);

    void delActivity(@Param("ids")String[] ids);
}