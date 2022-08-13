package com.interest.community.service;

import com.interest.community.pojo.Issue;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author Administrator
* @description 针对表【issue】的数据库操作Service
* @createDate 2022-08-02 15:53:48
*/
public interface IssueService extends IService<Issue> {

    /**
     * 消息展示-分页查询
     * @param current
     * @param pageSize
     * @return
     */
    List<Issue> getIssue(Integer current,Integer pageSize);

    //存入一条issue，图片调用OSS存入文件服务器，得到图片连接存入数据库
    boolean saveIssue(Issue issue, MultipartFile[] files);
}
