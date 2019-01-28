package com.bonc.boot.service;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bonc.boot.mapper.DicMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用：〈〉
 *
 * @author TanRW
 * @create 2019/1/26
 * @since 1.0.0
 */
@Service
@Transactional
public class DicService {

    @Resource
    DicMapper dicMapper;

    public JSONObject getDicType(String[] typeCode) {
        //查询出所有父类节点
        List<String> list=dicMapper.getAllParentCode(typeCode);
        JSONObject json = JSONUtil.createObj();
        for (String parentCode : list) {
            Map<String, List<Map<String, Object>>> map = new HashMap<>();
            List<Map<String, Object>> typeList = dicMapper.getActTypeByParentCode(parentCode);
            json.put(parentCode,typeList);
        }
        Console.log(json);
        return json;
    }
}