package com.zsb.pic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.zsb.pic.common.Constant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/29 12:42
 */
@Table(name = "t_picture", comment = "图片表")
@Data
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(isKey = true, isAutoIncrement = true, comment = "主键")
    private Long id;

    @Column(comment = "图片名称")
    private String name;

    @Column(comment = "图片分类")
    private String category;

    @Column(comment = "图片景区地点")
    private String location;

    @Column(comment = "图片描述", length = 1024)
    private String description;

    @Column(comment = "图片路径")
    private String url;

    @Column(comment = "上传者")
    private String username;

    @TableLogic
    @Column(comment = "是否删除", defaultValue = "0")
    private Boolean deleted;

    @Version
    @Column(comment = "乐观锁", defaultValue = "0")
    private Integer version;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT)
    @Column(name = "create_time", comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(name = "update_time", comment = "更新时间")
    private LocalDateTime updateTime;
}
