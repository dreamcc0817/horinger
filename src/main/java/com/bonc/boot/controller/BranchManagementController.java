package com.bonc.boot.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.bonc.boot.service.BranchManagementService;
import com.bonc.boot.service.FileService;
import com.bonc.boot.util.AppReply;
import com.bonc.boot.util.SessionUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作用：〈支部管理〉
 *
 * @author TanRW
 * @create 2019/1/25
 * @since 1.0.0
 */
@RestController
@RequestMapping("/branch")
@Validated
public class BranchManagementController {

    @Resource
    BranchManagementService branchManagementService;


    @GetMapping("/organize/selectActivity")
    public AppReply selectActivity(@RequestParam(value = "theme", required = false) String theme,
                                   @RequestParam(value = "typeCode", required = false) String typeCode,
                                   @RequestParam("pageNum")@NotEmpty String pageNum,
                                   @RequestParam("pageSize")@NotEmpty String pageSize,
                                   @RequestParam("allData")@NotEmpty String allData,
                                   @RequestParam("orgCode") String orgCode,
                                   HttpSession session) {
        String userId = SessionUtil.getUserId(session);
        List<Map<String, Object>> list = branchManagementService.selectActivity(theme, typeCode, pageNum, pageSize, orgCode, allData);
        return AppReply.success(new PageInfo<Map<String, Object>>(list));
    }

    @PostMapping("/organize/addOrUpdateActivity")
    public AppReply addOrUpdateActivity(@RequestParam(value = "id", required = false) String id,
                                        @RequestParam(value = "actTypeCode", required = true) String act_type_code,
                                        @RequestParam(value = "actTheme", required = true) String act_theme,
                                        @RequestParam(value = "actStartTime", required = true) String act_start_time,
                                        @RequestParam(value = "actEndTime", required = true) String act_end_time,
                                        @RequestParam(value = "actHost", required = true) String act_host,
                                        @RequestParam(value = "actRecorder", required = false) String act_recorder,
                                        @RequestParam(value = "actPlace", required = false) String act_place,
                                        @RequestParam(value = "actLeaderIds", required = false) String act_leader_ids,
                                        @RequestParam(value = "actPeopleIds", required = true) String act_people_ids,
                                        @RequestParam(value = "actContent", required = true) String act_content,
                                        @RequestParam(value = "orgCode", required = false) String org_code,
                                        @RequestParam(value = "addAndpub", required = false) String addAndpub,
                                        @RequestParam(value = "fileIds", required = false) String[] fileIds) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("act_type_code", act_type_code);
        map.put("act_theme", act_theme);
        map.put("act_start_time", Convert.toDate(act_start_time));
        map.put("act_end_time", Convert.toDate(act_end_time));
        map.put("act_host", act_host);
        map.put("act_recorder", act_recorder);
        map.put("act_place", act_place);
        map.put("act_leader_ids", act_leader_ids);
        map.put("act_people_ids", act_people_ids);
        map.put("act_content", act_content);
        map.put("org_code", org_code);
        map.put("act_pub_status", addAndpub);
        if ("1".equals(addAndpub)) {
            map.put("act_pub_time", DateUtil.date());
        } else {
            map.put("act_pub_time", null);
        }
        map.put("fileIds", fileIds);
        String msg = branchManagementService.addOrUpdateActivity(map);
        return AppReply.success();
    }

    @PutMapping("/organize/pubActivity")
    public AppReply pubActivity(@RequestParam(value = "ids",required = true)@NotEmpty String[] ids){
        String msg = branchManagementService.pubActivity(ids);
        return AppReply.success();
    }

    @DeleteMapping("/organize/delActivity")
    public AppReply delActivity(@RequestParam(value = "ids",required = true)@NotEmpty String[] ids){
        String msg = branchManagementService.delActivity(ids);
        return AppReply.success();
    }

    @PostMapping("/organize/addOrUpdateSummarize")
    public AppReply summarizeActivity(@RequestParam("id") String id,
                                      @RequestParam(value = "actReachedNum",required = false)String  act_reached_num,
                                      @RequestParam(value = "actAttendNum",required = false)String  act_attend_num,
                                      @RequestParam(value = "actAttendLeaderIds",required = false)String  act_attend_leader_ids,
                                      @RequestParam(value = "actAttendPeopleIds",required = false)String  act_attend_people_ids,
                                      @RequestParam(value = "actSummary",required = true)String  act_summary,
                                      @RequestParam(value = "fileIds",required = false)String[]  fileIds
                                      ){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("act_reached_num",act_reached_num);
        map.put("act_attend_num",act_attend_num);
        map.put("act_attend_leader_ids",act_attend_leader_ids);
        map.put("act_attend_people_ids",act_attend_people_ids);
        map.put("act_summary",act_summary);
        map.put("fileIds",fileIds);
        String msg = branchManagementService.addOrUpdateSummarize(map);
        return AppReply.success();
    }


    @GetMapping("/getActivityById")
    public AppReply getActivityById(@RequestParam("id") String id,
                                    @RequestParam("mark") String mark){
        Map<String,Object> map = branchManagementService.getActivityById(id,mark);
        return AppReply.success(map);
    }

    @GetMapping("/getMyActivity")
    public AppReply getMyActivity(@RequestParam(value = "actTheme" ,required = false) String act_theme,
                                  @RequestParam(value = "actTypeCode",required = false) String act_type_code,
                                  @RequestParam("pageNum")@NotEmpty String pageNum,
                                  @RequestParam("pageSize")@NotEmpty String pageSize,
                                  HttpSession session){
        String user = SessionUtil.getUserId(session);
        return AppReply.success();
    }
}