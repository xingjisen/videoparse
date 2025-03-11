package org.dromara.video.service;

/**
 * @author Jason
 * @PACKAGE_NAME com.video
 * @Description
 * @date 2025-02-18 22:00:29
 */
public interface VideoParseService {
    /**
     * 获取 tiktok视频数据接口。
     *
     * @param url 视频的URL地址
     * @return 返回视频文件的详细JSON信息
     */
    public String getVideoDownloadData(String url, String type);
}
