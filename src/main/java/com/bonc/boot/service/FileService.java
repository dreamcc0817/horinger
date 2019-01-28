package com.bonc.boot.service;

import cn.hutool.core.util.IdUtil;
import com.bonc.boot.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 作用：〈〉
 *
 * @author TanRW
 * @create 2019/1/25
 * @since 1.0.0
 */
@Service
@Transactional
public class FileService {

    @Resource
    FileMapper fileMapper;

    public String save(Map<String, Object> map) {
        String id = IdUtil.simpleUUID();
        map.put("id",id);
        fileMapper.save(map);
        return id;
    }

    public void updateByActId(String file_use_for ,String actId, String[] fileIds) {
        fileMapper.updateByActId(file_use_for,actId,fileIds);
    }

    public void cancelByActId(String file_use_for, String act_id) {
        fileMapper.cancelByActId(file_use_for,act_id);
    }

    public Map<String, String> findById(String id) {
        Map<String, String> map = fileMapper.findById(id);
        return map;
    }

    public List<Map<String, Object>> getFileList(String act_id) {
        List<Map<String, Object>> list = fileMapper.getFileList(act_id);
        return list;
    }

    public List<Map<String, Object>> getFileListByUseFor(String id, String mark) {
        List<Map<String, Object>> list = fileMapper.getFileListByUseFor(id,mark);
        return list;
    }
}