package com.interest.adapter.controller;

import com.alibaba.fastjson2.JSONObject;
import com.interest.adapter.client.CommunityClient;
import com.interest.entity.pojo.Issue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/adapter")
@Api(value = "转发社区消息服务API", tags = "转发社区消息服务API")
public class CommunityController {
    @Autowired
    private CommunityClient communityClient;

    @ApiOperation(value = "获取消息")
    @GetMapping(value = "/app/getIssue")
    public JSONObject getIssue(@RequestParam Integer current, @RequestParam Integer pageSize){
        return communityClient.getIssue(current,pageSize);
    }

    @PostMapping("/app/save")
    JSONObject saveIssue(@ApiParam(value = "一条动态") @RequestBody Issue issue){
        return communityClient.saveIssue(issue);
    }


}
