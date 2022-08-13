package com.interest.adapter.client;

import com.alibaba.fastjson2.JSONObject;
import com.interest.entity.pojo.Issue;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("community")
public interface CommunityClient {
    /**
     * 获取消息
     * @param current
     * @param pageSize
     * @return
     */
    @GetMapping("/app/getIssue")
    JSONObject getIssue(@RequestParam Integer current,@RequestParam Integer pageSize);

    @PostMapping("/app/save")
    JSONObject saveIssue(@ApiParam(value = "一条动态") @RequestBody Issue issue);

    /**
     * 发布一条消息
     */
//    JSONObject saveIssue(@ApiParam(value = "一条动态") @RequestBody Issue issue)
}
