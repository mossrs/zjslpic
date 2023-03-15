package com.zsb.pic.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/12/4 23:01
 */
@Data
public class PicDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long picId;

    private String name;

    private String category;

    private String location;

    private String description;

    private String uploadName;

    private MultipartFile file;


}
