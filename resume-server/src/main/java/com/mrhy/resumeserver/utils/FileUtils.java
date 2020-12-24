package com.mrhy.resumeserver.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件工具类
 *
 * @author mrhy
 */
@Log4j2
public class FileUtils {
    /**
     * 图片上传
     *
     * @param multipartFile
     * @param saveFilePath
     * @return
     */
    public static void imageUpload(MultipartFile multipartFile, String saveFilePath) throws Exception {
        File targetFile = new File(saveFilePath);
        if (targetFile.getParentFile() != null || !targetFile.getParentFile().isDirectory()) {
            //创建文件
            targetFile.getParentFile().mkdirs();
        }

        multipartFile.transferTo(new File(targetFile.getAbsolutePath()));
    }
}
