package com.bonc.boot.controller;

import cn.hutool.json.JSONObject;
import com.bonc.boot.service.DicService;
import com.bonc.boot.util.AppReply;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 作用：〈字段数据〉
 *
 * @author TanRW
 * @create 2019/1/26
 * @since 1.0.0
 */
@RestController
public class DicController {

    @Resource
    DicService dicService;

    @GetMapping("/party/getDicType")
    public AppReply getDicType(String[] typeCode){
        JSONObject list = dicService.getDicType(typeCode);
        return AppReply.success(list);
    }
}