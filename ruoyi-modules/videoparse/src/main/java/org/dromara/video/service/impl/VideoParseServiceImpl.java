package org.dromara.video.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.video.service.VideoParseService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jason
 * @PACKAGE_NAME com.video
 * @Description
 * @date 2025-02-18 22:00:29
 */
@Service
public class VideoParseServiceImpl implements VideoParseService {

    @Override
    public String getVideoDownloadData(@RequestParam String url, String type) {
        JSONObject jsonObject = parseVideoUrl(url);
        String data = jsonObject.toString();
        System.out.println("代码行🚀data~ -> " + data);
        return jsonObject.toString();
    }


    /**
     * 解析视频链接
     */
    public JSONObject parseVideoUrl(String url) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                "yt-dlp",
                // 获取下载JSON链接
                "-j",
//                "-J",
//                "-write-info-json",
                "--audio-multistreams",
                "--video-multistreams",
                "--prefer-free-formats",
                url
            );
            Process process = processBuilder.start();
            // 获取yt-dlp的输出流（下载链接）
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder downloadLink = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                // 拼接所有输出的链接
                downloadLink.append(line).append("\n");
            }
            process.waitFor();
            if (downloadLink.toString().equals("")) {
                throw new ServiceException("视频解析失败");
            }
            JSONObject jsonObject = JSONUtil.parseObj(downloadLink.toString().trim());
            return jsonObject;

        } catch (IOException | InterruptedException e) {
            throw new ServiceException("无法获取视频下载链接");
        }
    }
}
