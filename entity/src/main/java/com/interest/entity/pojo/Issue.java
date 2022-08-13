package com.interest.entity.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName issue
 */
@TableName(value ="issue")
@Data
public class Issue implements Serializable {
    /**
     * 发布一条帖子ID
     * 雪花算法生成
     */
    @ApiModelProperty(value = "发布一条帖子ID")
    @TableId(value = "issue_id",type = IdType.ASSIGN_ID)
    private String issueId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 帖子的类型，如二手交易、新闻动态
     */
    @ApiModelProperty(value = "帖子的类型，如二手交易、新闻动态")
    @TableField(value = "issue_type")
    private String issueType;

    /**
     * 帖子的文本
     */
    @ApiModelProperty(value = "帖子的文本")
    @TableField(value = "issue_information")
    private String issueInformation;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @TableField(value = "issue_image_url")
    private String issueImageUrl;

    /**
     * 转发ID
     */
    @ApiModelProperty(value = "转发ID")
    @TableField(value = "issue_relay_id")
    private String issueRelayId;

    /**
     * 转发数量
     */
    @ApiModelProperty(value = "转发数量")
    @TableField(value = "issue_relay_num")
    private Integer issueRelayNum;

    /**
     * 评论ID
     */
    @ApiModelProperty(value = "评论ID")
    @TableField(value = "issue_comment_id")
    private String issueCommentId;

    /**
     * 评论数量
     */
    @ApiModelProperty(value = "评论数量")
    @TableField(value = "issue_comment_num")
    private Integer issueCommentNum;

    /**
     * 点赞ID
     */
    @ApiModelProperty(value = "点赞ID")
    @TableField(value = "issue_praise_id")
    private String issuePraiseId;

    /**
     * 点赞数量
     */
    @ApiModelProperty(value = "点赞数量")
    @TableField(value = "issue_praise_num")
    private Integer issuePraiseNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "issue_status")
    private Integer issueStatus;

    /**
     * 发布日期
     */
    @ApiModelProperty(value = "发布日期")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后一次修改日期
     */
    @ApiModelProperty(value = "最后一次修改日期")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Issue other = (Issue) that;
        return (this.getIssueId() == null ? other.getIssueId() == null : this.getIssueId().equals(other.getIssueId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIssueType() == null ? other.getIssueType() == null : this.getIssueType().equals(other.getIssueType()))
            && (this.getIssueInformation() == null ? other.getIssueInformation() == null : this.getIssueInformation().equals(other.getIssueInformation()))
            && (this.getIssueImageUrl() == null ? other.getIssueImageUrl() == null : this.getIssueImageUrl().equals(other.getIssueImageUrl()))
            && (this.getIssueRelayId() == null ? other.getIssueRelayId() == null : this.getIssueRelayId().equals(other.getIssueRelayId()))
            && (this.getIssueRelayNum() == null ? other.getIssueRelayNum() == null : this.getIssueRelayNum().equals(other.getIssueRelayNum()))
            && (this.getIssueCommentId() == null ? other.getIssueCommentId() == null : this.getIssueCommentId().equals(other.getIssueCommentId()))
            && (this.getIssueCommentNum() == null ? other.getIssueCommentNum() == null : this.getIssueCommentNum().equals(other.getIssueCommentNum()))
            && (this.getIssuePraiseId() == null ? other.getIssuePraiseId() == null : this.getIssuePraiseId().equals(other.getIssuePraiseId()))
            && (this.getIssuePraiseNum() == null ? other.getIssuePraiseNum() == null : this.getIssuePraiseNum().equals(other.getIssuePraiseNum()))
            && (this.getIssueStatus() == null ? other.getIssueStatus() == null : this.getIssueStatus().equals(other.getIssueStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIssueId() == null) ? 0 : getIssueId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIssueType() == null) ? 0 : getIssueType().hashCode());
        result = prime * result + ((getIssueInformation() == null) ? 0 : getIssueInformation().hashCode());
        result = prime * result + ((getIssueImageUrl() == null) ? 0 : getIssueImageUrl().hashCode());
        result = prime * result + ((getIssueRelayId() == null) ? 0 : getIssueRelayId().hashCode());
        result = prime * result + ((getIssueRelayNum() == null) ? 0 : getIssueRelayNum().hashCode());
        result = prime * result + ((getIssueCommentId() == null) ? 0 : getIssueCommentId().hashCode());
        result = prime * result + ((getIssueCommentNum() == null) ? 0 : getIssueCommentNum().hashCode());
        result = prime * result + ((getIssuePraiseId() == null) ? 0 : getIssuePraiseId().hashCode());
        result = prime * result + ((getIssuePraiseNum() == null) ? 0 : getIssuePraiseNum().hashCode());
        result = prime * result + ((getIssueStatus() == null) ? 0 : getIssueStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", issueId=").append(issueId);
        sb.append(", userId=").append(userId);
        sb.append(", issueType=").append(issueType);
        sb.append(", issueInformation=").append(issueInformation);
        sb.append(", issueImageUrl=").append(issueImageUrl);
        sb.append(", issueRelayId=").append(issueRelayId);
        sb.append(", issueRelayNum=").append(issueRelayNum);
        sb.append(", issueCommentId=").append(issueCommentId);
        sb.append(", issueCommentNum=").append(issueCommentNum);
        sb.append(", issuePraiseId=").append(issuePraiseId);
        sb.append(", issuePraiseNum=").append(issuePraiseNum);
        sb.append(", issueStatus=").append(issueStatus);
        sb.append(", issuePublicationDate=").append(createTime);
        sb.append(", issueLastModifiedData=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}