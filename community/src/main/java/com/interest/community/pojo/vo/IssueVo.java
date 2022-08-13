package com.interest.community.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value="信息展示", description="")
@Data
public class IssueVo {
    /**
     * 发布一条帖子ID
     */
    @ApiModelProperty(value = "发布一条帖子ID")
    private String issueId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 帖子的类型，如二手交易、新闻动态
     */
    @ApiModelProperty(value = "帖子的类型，如二手交易、新闻动态")
    private String issueType;

    /**
     * 帖子的文本
     */
    @ApiModelProperty(value = "帖子的文本")
    private String issueInformation;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String issueImageUrl;

    /**
     * 转发ID
     */
    @ApiModelProperty(value = "转发ID")
    private String issueRelayId;

    /**
     * 转发数量
     */
    @ApiModelProperty(value = "转发数量")
    private Integer issueRelayNum;

    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID")
    private String issueCommentId;

    /**
     * 评论数量
     */
    @ApiModelProperty(value = "评论数量")
    private Integer issueCommentNum;

    /**
     * 点赞ID
     */
    @ApiModelProperty(value = "点赞ID")
    private String issuePraiseId;

    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量")
    private Integer issuePraiseNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer issueStatus;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    private Date issueCreateTime;

    /**
     * 最后一次修改日期
     */
    @ApiModelProperty(value = "最后一次修改日期")
    private Date issueUpdateTime;


}
