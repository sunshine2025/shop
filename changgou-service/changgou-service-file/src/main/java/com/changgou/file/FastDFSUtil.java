package com.changgou.file;

import org.csource.fastdfs.*;
import org.omg.CORBA.NameValuePair;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FastDFSUtil {
    private static String filename = null;

    /**
     * 加载Tracker链接信息
     */
    static {
        try {
            //查找classpath下的文件路径
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            //加载Tracker链接信息
            ClientGlobal.init(filename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{
        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = trackerClient.getConnection();

        StorageClient storageClient = new StorageClient(trackerServer,null);

        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(),fastDFSFile.getExt(),null);

        return uploads;
    }
    /**
     * 文件下载
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception{
        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = trackerClient.getConnection();

        StorageClient storageClient = new StorageClient(trackerServer,null);

        byte[] buffer = storageClient.download_file(groupName,remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws Exception
     */
    public static FileInfo getFile(String groupName,String remoteFileName) throws Exception{
        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = trackerClient.getConnection();

        StorageClient storageClient = new StorageClient(trackerServer,null);

        return storageClient.get_file_info(groupName,remoteFileName);
    }

    public static void main(String[] args) throws Exception{
//        FileInfo fileInfo = getFile("group1","");
//        System.out.println(fileInfo.getSourceIpAddr());
//        System.out.println(fileInfo.getFileSize());

        InputStream is = downloadFile("group1","M00/00/00/rBUABF6mw8qAGZYrAAK5njhhEhg581.jpg");
        FileOutputStream os = new FileOutputStream("D:/work/RE/changgou/study/img/123.jpg");
        byte[] buffer = new byte[1024];
        while (is.read(buffer) != -1){
            os.write(buffer);
        }
        os.close();
        is.close();
    }
}
