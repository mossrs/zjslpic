package com.zsb.pic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsb.pic.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/30 15:04
 */
@Mapper
public interface PictureMapper extends BaseMapper<Picture> {
}
