package com.changgou.file.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.file.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin
public class FileUploadController {
    @PostMapping
    public Result upload(@RequestParam(value = "file")MultipartFile file) throws Exception{
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        );

        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        String url = "http://49.233.42.247:8080/"+uploads[0]+"/"+uploads[1];
        return new Result(true, StatusCode.OK,"成功！",url);
    }

    @GetMapping(value = "/down/{groupName}/{remoteFileName}")
    public Result downloadFile(@PathVariable(value = "groupName") String groupName, @PathVariable(value = "remoteFileName") String remoteFileName) throws Exception {
        InputStream inputStream = FastDFSUtil.downloadFile(groupName,remoteFileName);
        byte[] bytes = new byte[inputStream.available()];
        File file = new File("/img/a.jpg");
        FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(file);
        inputStream.read(bytes);
        fileImageOutputStream.write(bytes);
        return new Result(true, StatusCode.OK,"成功！");
    }
}
