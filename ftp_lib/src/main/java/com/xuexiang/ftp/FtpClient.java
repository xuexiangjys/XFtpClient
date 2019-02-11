package com.xuexiang.ftp;

/**
 * FTP客户端
 *
 * @author xuexiang
 * @since 2019/1/2 上午11:15
 */
public class FtpClient {

    static {
        System.loadLibrary("ftp-lib");
    }

    /**
     * 连接到ftp服务器
     *
     * @param ipAddress
     * @return
     */
    public native boolean connect(String ipAddress);

    /**
     * 登陆ftp
     *
     * @param loginName
     * @param password
     * @return
     */
    public native boolean login(String loginName, String password);

    /**
     * 连接到ftp服务器并登陆
     *
     * @param ipAddress
     * @param loginName
     * @param password
     * @return
     */
    public native boolean login(String ipAddress, String loginName, String password);

    /**
     * 文件下载
     *
     * @param remoteFile 远端需要下载的文件路径
     * @param localPath  保存到本地的根目录
     * @return
     */
    public native boolean download(String remoteFile, String localPath);

    /**
     * 文件上传
     *
     * @param localFile  本地要上传的文件路径
     * @param remotePath 远端要上传的根目录
     * @return
     */
    public native boolean upload(String localFile, String remotePath);

    /**
     * 退出登录
     *
     * @return
     */
    public native boolean quit();


}
