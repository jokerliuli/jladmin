package com.jokerliu.system.controller;

import com.google.gson.Gson;
import com.jokerliu.entity.Result;
import com.jokerliu.enums.ResultStatusCode;
import com.jokerliu.system.entity.SysPictureManage;
import com.jokerliu.system.service.ISysPictureManageService;
import com.jokerliu.utils.FileSize;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author JokerLiu
 * @create 2019-02-19 13:36
 * @desc 上传到七牛存储对象
 **/
@RestController
@RequestMapping(value = "admin/upload")
public class QinuiUploadController {

    @Resource
    private ISysPictureManageService iSysPictureManageService;

    @Value("${qiniu.accessKey}")
    private String accessKey;
    @Value("${qiniu.secretKey}")
    private String secretKey;
    @Value("${qiniu.bucketName}")
    private String bucket;
    @Value("${qiniu.domainOfBucket}")
    private String domainOfBucket;

    @PostMapping("/qiniu")
    public Result upload(@RequestParam(value = "file") MultipartFile file) {
        //构造一个带指定Zone对象的配置类(华东)
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //格式化命名，去除所有空格，增加分割符“-时间戳”
        String oldFileName = file.getOriginalFilename().replace(" ", "");
        String bName = oldFileName.substring(0,oldFileName.lastIndexOf("."));
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String timestamp = String.valueOf(System.currentTimeMillis());
        String newFileName = bName+"-"+timestamp+ eName;
        String key = newFileName;
        File localFile;
        String finalUrl = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            Response response = uploadManager.put(localFile, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);

//            String fileName = "公司/存储/qiniu.jpg";
//            String domainOfBucket = "http://devtools.qiniu.com";
//            String encodedFileName = URLEncoder.encode(fileName, "utf-8");
//            String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
//            System.out.println(finalUrl);

            String fileName = key;
            String encodedFileName = URLEncoder.encode(fileName, "utf-8");
            finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
            System.out.println(finalUrl);

            BufferedImage sourceImg = ImageIO.read(new FileInputStream(localFile));
            SysPictureManage sysPictureManage = new SysPictureManage();
            sysPictureManage.setPictureName(newFileName);
            sysPictureManage.setPictureSize(FileSize.getPrintSize(file.getSize()));
            sysPictureManage.setPictureUrl(finalUrl);
            sysPictureManage.setPictureDimension(sourceImg.getWidth() + "*" + sourceImg.getHeight());
            iSysPictureManageService.save(sysPictureManage);

        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(ResultStatusCode.OK,finalUrl);
    }
}
