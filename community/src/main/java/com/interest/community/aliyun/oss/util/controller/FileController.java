package com.interest.community.aliyun.oss.util.controller;

import com.alibaba.fastjson2.JSONObject;
import com.interest.common.base.exception.BusinessException;
import com.interest.common.base.result.R;
import com.interest.common.base.result.ResponseEnum;
import com.interest.community.aliyun.oss.util.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Api
@RestController
@RequestMapping("/app/oss/file")
public class FileController {
    @Resource
    private FileService fileService;


    /**
     * 文件上传
     */
    @ApiOperation("文件上传")
    @PostMapping("upload")
    public JSONObject fileUpload(@ApiParam(value = "文件",required = true)
                    @RequestParam("file") MultipartFile file ,

                    @ApiParam(value = "模块",required = true)
                    @RequestParam("module") String module){

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);
            JSONObject returnMsg = new JSONObject();
            returnMsg.put("RespInfo",R.ok().message("文件上传成功"));
            returnMsg.put("uploadUrl",uploadUrl);

            return returnMsg;

            //上传

        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }


    }



    /**
     * 文件删除
     */
    @ApiOperation("文件删除")
    @PostMapping("deleetFile")
    public JSONObject deleteFile(@ApiParam(value = "文件路径",required = true)
                           @RequestParam("fileUrl") String fileUrl){
        JSONObject returnMsg = new JSONObject();
        R RespInfo = fileService.deleteOneFile(fileUrl);

        returnMsg.put("RespInfo",R.ok().message("文件删除成功"));

        return returnMsg;
    }



}
