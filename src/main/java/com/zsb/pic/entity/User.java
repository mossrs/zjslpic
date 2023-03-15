package com.zsb.pic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.zsb.pic.common.Constant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author z's'b
 */
@Data
@Table(name = "t_user", comment = "用户表")
//@Validated 在对象上加 然后给每个参数叫上指定校验
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id", isKey = true, isAutoIncrement = true, comment = "主键")
    private Long id;

    @Column(comment = "账户名")
    private String username;

    @Column(comment = "密码")
    @TableField(select = false)
    private String password;

    @Column(comment = "是否是管理员", defaultValue = "false")
    private Boolean isAdmin;

    @TableLogic
    @Column(comment = "是否删除", defaultValue = "false")
    private Boolean deleted;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT)
    @Column(name = "create_time", comment = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = Constant.R_DATE_TIME_FORMAT, timezone = Constant.TIME_ZONE, locale = Constant.LOCALE)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(name = "update_time", comment = "更新时间")
    private LocalDateTime updateTime;
}
