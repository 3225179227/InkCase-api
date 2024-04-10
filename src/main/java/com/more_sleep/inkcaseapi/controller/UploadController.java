package com.more_sleep.inkcaseapi.controller;


import com.more_sleep.inkcaseapi.common.Code;
import com.more_sleep.inkcaseapi.common.R;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);


    @Value("${me.upload.path}")
    private String baseFolderPath;

    @PostMapping("/upload")
    public R upload(HttpServletRequest request, MultipartFile image) {

        R r = new R();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        StringBuffer url = new StringBuffer();
        String filePath = sdf.format(new Date());

        File baseFolder = new File(baseFolderPath + filePath);
        if (!baseFolder.exists()) {
            baseFolder.mkdirs();
        }

        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append("/")
                .append(filePath);

        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");

        try {

            File dest = new File(baseFolder, imgName);
            image.transferTo(dest);

            url.append("/").append(imgName);

            r.setCode(Code.SUCCESS.getCode());

            r.setData(Map.of("url", url));

        } catch (IOException e) {
            logger.error("文件上传错误 , uri: {} , caused by: ", request.getRequestURI(), e);
            r.setCode(Code.UPLOAD_ERROR.getCode());
        }

        return r;
    }
}
