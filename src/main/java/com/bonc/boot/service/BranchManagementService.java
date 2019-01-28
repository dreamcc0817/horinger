package com.bonc.boot.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.bonc.boot.mapper.BranchManagementMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
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
public class BranchManagementService {

    @Resource
    BranchManagementMapper branchManagementMapper;

    @Resource
    FileService fileService;

    public List<Map<String, Object>> selectActivity(String theme, String typeCode, String pageNum, String pageSize, String orgCode, String allData) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("theme", theme);
        map.put("typeCode", typeCode);
        map.put("orgCode", orgCode);
        map.put("allData", allData);
        map.put("orgCode", orgCode);
        PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
        List<Map<String, Object>> list = branchManagementMapper.selectActivity(map);
        return list;
    }

    public String addOrUpdateActivity(Map<String, Object> map) {
        //新加
        if (ObjectUtil.isNull(map.get("id")) || StrUtil.isEmpty(map.get("id").toString())) {
            String act_id = IdUtil.simpleUUID();
            map.put("id", act_id);
            //保存党组织活动中的内容
            branchManagementMapper.saveActivity(map);
            //取消文件的所有关联
            String file_use_for = "0";
            fileService.cancelByActId(file_use_for, act_id);
            //再次关联附件与内容的关系
            if (ObjectUtil.isNotNull(map.get("fileIds"))) {
                String[] fileIds = (String[]) map.get("fileIds");
                fileService.updateByActId(file_use_for, act_id, fileIds);
            }
        } else {
            branchManagementMapper.updateActivity(map);

            //取消文件的所有关联
            String file_use_for = "0";
            fileService.cancelByActId(file_use_for, map.get("id").toString());
            //再次关联附件与内容的关系
            if (ObjectUtil.isNotNull(map.get("fileIds"))) {
                String[] fileIds = (String[]) map.get("fileIds");
                fileService.updateByActId(file_use_for, map.get("id").toString(), fileIds);
            }
        }
        return null;
    }

    public String addOrUpdateSummarize(Map<String, Object> map) {

        String act_id = map.get("id").toString();
        //保存党组织活动中的内容
        branchManagementMapper.updateSummarize(map);
        //取消文件的所有关联
        String file_use_for = "1";
        fileService.cancelByActId(file_use_for, act_id);
        //再次关联附件与内容的关系
        if (ObjectUtil.isNotNull(map.get("fileIds"))) {
            String[] fileIds = (String[]) map.get("fileIds");
            fileService.updateByActId(file_use_for, act_id, fileIds);
        }
        return null;

    }

    public Map<String, Object> getActivityById(String id, String mark) {
        Map<String, Object> map = branchManagementMapper.getActivityById(id);
        List<Map<String,Object>> list = fileService.getFileListByUseFor(id,mark);
        map.put("appendixList",list);
        return map;
    }

    public String pubActivity(String[] ids) {
        branchManagementMapper.pubActivity(ids);
        return null;
    }

    public String delActivity(String[] ids) {
        branchManagementMapper.delActivity(ids);
        return null;
    }
}