package com.zsb.pic.controller;

import com.zsb.pic.common.R;
import com.zsb.pic.dto.MyPageDTO;
import com.zsb.pic.dto.PicDTO;
import com.zsb.pic.entity.Picture;
import com.zsb.pic.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/30 18:02
 */
@RestController
@RequestMapping("/photo")
@Slf4j
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/getPics")
    public R<List<Picture>> getPics() {
        return R.ok(pictureService.getPics());
    }

    @GetMapping("/getPagePics/{page}/{size}")
    public R<MyPageDTO<Picture>> getPagePics(@PathVariable Long page, @PathVariable Long size) {
        return R.ok(pictureService.getPagePics(page, size));
    }

    @GetMapping("/getUserPics")
    public R<List<Picture>> getUserPics(String username) {
        return R.ok(pictureService.getUserPics(username));
    }

    @GetMapping("/getPageUserPics/{page}/{size}")
    public R<MyPageDTO<Picture>> getPageUserPics(@PathVariable Long page, @PathVariable Long size, String username) {
        return R.ok(pictureService.getPageUserPics(page, size, username));
    }

    @GetMapping("/getSearchPics")
    public R<List<Picture>> getSearchPics(String picCategory) {
        return R.ok(pictureService.getSearchPics(picCategory));
    }

    @GetMapping("/getPageSearchPics/{page}/{size}")
    public R<MyPageDTO<Picture>> getPageSearchPics(@PathVariable Long page, @PathVariable Long size, String picCategory) {
        return R.ok(pictureService.getPageSearchPics(page, size, picCategory));
    }

    @DeleteMapping("/deletePic")
    public R<Boolean> deletePic(Long picId, String url) {
        Boolean flag = pictureService.deletePic(picId, url);
        return flag ? R.ok(true) : R.err(false);
    }

    @PutMapping("/updatePic")
    public R<Boolean> updatePic(Long picId,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String category,
                                @RequestParam(required = false) String location,
                                @RequestParam(required = false) String description,
                                @RequestParam(required = false) String uploadName,
                                @RequestParam(required = false) MultipartFile file) {
        Boolean flag = pictureService.updatePic(picId, name, category, location, description, uploadName, file);
        return flag ? R.ok(true) : R.err(false);
    }

    @PostMapping("/uploadPic")
    public R<Picture> uploadPic(String name, String category, String location,
                                String description, String uploadName, MultipartFile file) {
        return R.ok(pictureService.uploadPic(name, category, location, description, uploadName, file));
    }
}
