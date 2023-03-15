package com.zsb.pic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.pic.common.utils.FileUtils;
import com.zsb.pic.dto.MyPageDTO;
import com.zsb.pic.entity.Picture;
import com.zsb.pic.mapper.PictureMapper;
import com.zsb.pic.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/30 18:00
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> getPics() {
        return pictureMapper.selectList(null);
    }

    @Override
    public MyPageDTO<Picture> getPagePics(Long page, Long size) {
        Page<Picture> picturePage = pictureMapper.selectPage(new Page<>(page, size), null);
        return new MyPageDTO<>(picturePage);
    }

    @Override
    public List<Picture> getUserPics(String username) {
        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return pictureMapper.selectList(wrapper);
    }

    @Override
    public MyPageDTO<Picture> getPageUserPics(Long page, Long size, String username) {
        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Page<Picture> picturePage = pictureMapper.selectPage(new Page<>(page, size), wrapper);
        return new MyPageDTO<>(picturePage);
    }

    @Override
    public List<Picture> getSearchPics(String picCategory) {
        QueryWrapper<Picture> qw = new QueryWrapper<>();
        if ("大运河诗路".equals(picCategory)) {
            qw.eq("category", "大运河诗路");
            return pictureMapper.selectList(qw);
        } else if ("浙东唐诗之路".equals(picCategory)) {
            qw.eq("category", "浙东唐诗之路");
            return pictureMapper.selectList(qw);
        } else if ("瓯江山水诗路".equals(picCategory)) {
            qw.eq("category", "瓯江山水诗路");
            return pictureMapper.selectList(qw);
        } else if ("钱塘江诗路".equals(picCategory)) {
            qw.eq("category", "钱塘江诗路");
            return pictureMapper.selectList(qw);
        }
        return new ArrayList<>();
    }

    @Override
    public MyPageDTO<Picture> getPageSearchPics(Long page, Long size, String picCategory) {
        QueryWrapper<Picture> qw = new QueryWrapper<>();
        if ("大运河诗路".equals(picCategory)) {
            qw.eq("category", "大运河诗路");
            Page<Picture> picturePage = pictureMapper.selectPage(new Page<>(page, size), qw);
            return new MyPageDTO<>(picturePage);
        } else if ("浙东唐诗之路".equals(picCategory)) {
            qw.eq("category", "浙东唐诗之路");
            Page<Picture> picturePage = pictureMapper.selectPage(new Page<>(page, size), qw);
            return new MyPageDTO<>(picturePage);
        } else if ("瓯江山水诗路".equals(picCategory)) {
            qw.eq("category", "瓯江山水诗路");
            Page<Picture> picturePage = pictureMapper.selectPage(new Page<>(page, size), qw);
            return new MyPageDTO<>(picturePage);
        } else if ("钱塘江诗路".equals(picCategory)) {
            qw.eq("category", "钱塘江诗路");
            Page<Picture> picturePage = pictureMapper.selectPage(new Page<>(page, size), qw);
            return new MyPageDTO<>(picturePage);
        }
        return new MyPageDTO<>();
    }

    @Override
    public Boolean deletePic(Long picId, String url) {
        try {
            FileUtils.deletePic(url);
            return pictureMapper.deleteById(picId) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public Boolean updatePic(Long picId, String name, String category, String location,
                             String description, String uploadName, MultipartFile file) {
        Picture picture = new Picture();
        picture.setId(picId);
        if (name != null) {
            picture.setName(name);
        }
        if (category != null) {
            picture.setCategory(category);
        }
        if (location != null) {
            picture.setLocation(location);
        }
        if (description != null) {
            picture.setDescription(description);
        }
        if (uploadName != null) {
            picture.setUsername(uploadName);
        }
        try {
            if (file != null) {
                String url = FileUtils.uploadPic(file, uploadName);
                picture.setUrl(url);
            }
            return pictureMapper.updateById(picture) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败");
        }
    }

    @Override
    public Picture uploadPic(String name, String category, String location,
                             String description, String uploadName, MultipartFile file) {
        Picture picture = new Picture();
        picture.setName(name);
        picture.setCategory(category);
        picture.setLocation(location);
        picture.setDescription(description);
        picture.setUsername(uploadName);
        try {
            String url = FileUtils.uploadPic(file, uploadName);
            picture.setUrl(url);
            pictureMapper.insert(picture);
            return picture;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("新增图片失败");
        }
    }
}
