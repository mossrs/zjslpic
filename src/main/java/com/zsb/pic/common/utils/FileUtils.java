package com.zsb.pic.common.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/30 21:32
 */
@Slf4j
public class FileUtils {

    private static final String SECRET_ID = "";
    private static final String SECRET_KEY = "";
    private static final String REGION = "";
    private static final String BUCKET_NAME = "";
    private static final String DOMAIN = "";


    /**
     * @param file       文件
     * @param uploadName 上传人
     * @return 文件路径相对路径
     * @throws IOException         IO异常
     *                             腾讯云存储异常
     * @throws CosClientException  COS客户端异常
     * @throws CosServiceException COS服务端异常
     */
    public static String uploadPic(MultipartFile file, String uploadName) throws IOException {
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        ClientConfig clientConfig = new ClientConfig(new Region(REGION));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        String picDecodeName = UUID.randomUUID().toString().replaceAll("-", "")
                + file.getOriginalFilename();
        String url = "/" + uploadName + "/" + new DateTime().toString("yyyy/MM/dd") + "/" + picDecodeName;
        InputStream inputStream = file.getInputStream();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStream.available());
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                BUCKET_NAME,
                url,
                inputStream,
                objectMetadata);
        cosClient.putObject(putObjectRequest);
        cosClient.shutdown();
        inputStream.close();
        return url;
    }

    /**
     * @param key 就是在存储桶里的相对路径
     * @throws CosClientException  COS客户端异常
     * @throws CosServiceException COS服务端异常
     */
    public static void deletePic(String key) {
        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        ClientConfig clientConfig = new ClientConfig(new Region(REGION));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 设置 socket 读取超时，默认 30s
        clientConfig.setSocketTimeout(30 * 1000);
        // 设置建立连接超时，默认 30s
        clientConfig.setConnectionTimeout(30 * 1000);
        COSClient cosClient = new COSClient(cred, clientConfig);
        cosClient.deleteObject(BUCKET_NAME, key);
        cosClient.shutdown();
    }
}

