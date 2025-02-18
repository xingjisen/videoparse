package org.dromara.video.controller;

import org.dromara.common.core.domain.R;
import org.dromara.common.web.core.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Jason
 * @PACKAGE_NAME com.video
 * @Description
 * @date 2025-02-18 22:00:29
 */
@RestController
@RequestMapping("/video")
public class TestController extends BaseController {
    @GetMapping("/test")
    public R<Void> Test(String url) {
        try {
            // 设置当前目录
            System.setProperty("user.dir", "./video");
            Process process = Runtime.getRuntime().exec("E:/Development/ffmpeg/ffmpeg-7.0.2-essentials_build/bin/yt-dlp -o \"%(title)s.%(ext)s\" \"" + url + "\"");
            printResults(process, "GBK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }

    public void printResults(Process process, String charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), charset));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            // 检查是否有下载文件信息
            if (line.contains(".mp4")) {
                String temp = line.replaceAll("\\[download\\] ", "");
                temp = temp.replaceAll("Destination: ", "");
                String[] fileMsg = temp.split("\\.");
                // has already beedownloaded
                // [download] Destination:
                // [download]
                System.out.println("下载的文件名: " + fileMsg[0]);
                System.out.println("文件扩展名: " + fileMsg[1]);
            }
        }
    }


}
