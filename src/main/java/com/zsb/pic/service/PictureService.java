package com.zsb.pic.service;

import com.zsb.pic.dto.MyPageDTO;
import com.zsb.pic.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/30 18:00
 */
public interface PictureService {

    /**
     * 返回所有图片
     *
     * @return 所有图片
     */
    List<Picture> getPics();

    /**
     * 返回用户所有图片
     *
     * @param page 页码
     * @param size 每页数量
     * @return 用户所有图片
     */
    MyPageDTO<Picture> getPagePics(Long page, Long size);

    /**
     * 查找指定用户所有图片
     *
     * @param username 用户民
     * @return 用户图片列表
     */
    List<Picture> getUserPics(String username);

    /**
     * 查找指定用户的图片
     *
     * @param page     页码
     * @param size     每页数量
     * @param username 用户名
     * @return 用户图片列表
     */
    MyPageDTO<Picture> getPageUserPics(Long page, Long size, String username);

    /**
     * 根据条件返回不同诗路的图片列表
     *
     * @param picCategory 条件
     * @return 图片列表
     */
    List<Picture> getSearchPics(String picCategory);

    /**
     * 根据条件返回不同诗路的分页图片列表
     *
     * @param page        页码
     * @param size        每页数量
     * @param picCategory 条件
     * @return 图片列表
     */
    MyPageDTO<Picture> getPageSearchPics(Long page, Long size, String picCategory);

    /**
     * 删除指定id图片
     *
     * @param picId 图片id
     * @param url   图片url
     * @return 布尔
     */
    Boolean deletePic(Long picId, String url);

    /**
     * 更新图片信息
     *
     * @param picId       图片id
     * @param name        图片名
     * @param category    图片分类
     * @param location    图片位置
     * @param description 图片描述
     * @param uploadName  上传者
     * @param file        图片文件
     * @return 布尔
     */
    Boolean updatePic(Long picId, String name, String category, String location,
                      String description, String uploadName, MultipartFile file);

    /**
     * 上传图片
     *
     * @param name        图片名
     * @param category    图片分类
     * @param location    图片位置
     * @param description 图片描述
     * @param uploadName  上传者
     * @param file        图片文件
     * @return 返回图片信息
     */
    Picture uploadPic(String name, String category, String location,
                      String description, String uploadName, MultipartFile file);

}
