package com.interest.community.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.interest.community.aliyun.oss.util.service.FileService;
import com.interest.community.pojo.Issue;
import com.interest.community.service.IssueService;
import com.interest.community.mapper.IssueMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
* @author Administrator
* @description 针对表【issue】的数据库操作Service实现
* @createDate 2022-08-02 15:53:48
*/
@Service
@Slf4j
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue>
    implements IssueService{

    @Resource
    private IssueMapper issueMapper;

    @Resource
    private FileService fileService;

    /**
     * 消息展示-分页查询
     * @param current
     * @param pageSize
     * @return
     */
    @Override
    public List<Issue> getIssue(Integer current,Integer pageSize) {
        QueryWrapper<Issue> objectQueryWrapper = new QueryWrapper<>();
        Page<Issue> page = new Page<>(current,pageSize);
        Page<Issue> page1 = issueMapper.selectPage(page, null);

        List<Issue> records = page1.getRecords();

        return records;
    }

    /**
     * 发布消息
     * @param issue
     * @param files
     * @return
     */
    @Override
    public boolean saveIssue(Issue issue, MultipartFile[] files) {
        log.info("saveIssue");
        boolean isIssueSuccess =true;
        String[] issueImageUrls = new String[9];
        //判断files数组不能为空并且长度大于0
        if (files!=null && files.length>0){
            //循环上传多个文件
            for (int i = 0;i < files.length;i++){
                MultipartFile file =  files[i];
                try {
                    InputStream inputStream = file.getInputStream();
                    String originalFilename = file.getOriginalFilename();
                    String issueImageUrl = fileService.upload(inputStream, "issue", originalFilename);
                    issueImageUrls[i]=issueImageUrl;
                } catch (IOException e) {
                    e.printStackTrace();
                    isIssueSuccess=false;
                }

            }

//            使用fastjson转化   数组 =》字符串
            String issueImageUrl = JSON.toJSONString(issueImageUrls);
            System.out.println(issueImageUrl);
            //存入实体类
            issue.setIssueImageUrl(issueImageUrl);

            issueMapper.insert(issue);
        }
        return isIssueSuccess;
    }
}




