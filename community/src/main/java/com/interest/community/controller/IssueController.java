package com.interest.community.controller;


import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.interest.common.result.R;
import com.interest.community.pojo.Issue;
import com.interest.community.pojo.vo.IssueVo;
import com.interest.community.service.IssueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "app-首页-信息展示")
@RequestMapping("/app")
@RestController
@Slf4j
public class IssueController {

    @Autowired
    private IssueService issueService;

    @ApiOperation(value = "发布消息")
    @PostMapping("/save")
    public JSONObject saveIssue(@ApiParam(value = "一条动态") @RequestPart("issue") Issue issue, @RequestPart("files") MultipartFile[] files){
        JSONObject jsonObject = new JSONObject();
//        boolean save = issueService.save(issue);
        log.info("saveIssueCon");
        System.out.println("saveIssueCon");
        boolean isSave = issueService.saveIssue(issue,files);
        if (isSave) {
            jsonObject.put("RespInfo", R.ok());
        }else {
            jsonObject.put("RespInfo",R.error());
        }
        return jsonObject;
    }

    /**
     * 消息展示-分页查询
     * @param current
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "消息展示")
    @GetMapping  ("/getIssue")
    @ApiOperationSupport(params = @DynamicParameters(name = "page",properties = {
            @DynamicParameter(name = "current",value="起始页"),
            @DynamicParameter(name = "pageSize",value = "页面大小")
    }))
    public JSONObject getIssue(@RequestParam Integer current,@RequestParam Integer pageSize){
        JSONObject returnMsg = new JSONObject();
//        System.out.println(jsonObject);
        List<Issue> issueList= issueService.getIssue(current,pageSize);
        returnMsg.put("issueList",issueList);
        return returnMsg;
    }


    //点赞
    @ApiOperation(value = "点赞")
    @GetMapping("/doPraise/{issueId}")
    public JSONObject doPraise(@PathVariable("issueId") String issueId){
        JSONObject returnMsg = new JSONObject();
        log.info("doPraise");
        UpdateWrapper<Issue> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("issue_id",issueId);
        updateWrapper.setSql("issue_praise_num=issue_praise_num+1");
        boolean isUpdate = issueService.update(updateWrapper);
        if (isUpdate){
            returnMsg.put("RespInfo",R.ok().message("修改成功"));
        }else {
            returnMsg.put("RespInfo",R.error().message("修改失败"));
        }

        return returnMsg;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("redisTest")
    public String testRedis() {
        //设置值到redis
        redisTemplate.opsForValue().set("name","lucy");
        //从redis获取值
        String name = (String)redisTemplate.opsForValue().get("name");
        return name;
    }


}
