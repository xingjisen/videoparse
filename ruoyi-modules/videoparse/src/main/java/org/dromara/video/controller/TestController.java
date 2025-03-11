package org.dromara.video.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.web.core.BaseController;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Jason
 * @PACKAGE_NAME com.video
 * @Description
 * @date 2025-02-18 22:00:29
 */
//@RestController
//@RequestMapping("/video")
public class TestController extends BaseController {
    /**
     * 测试接口，用于下载视频并保存到本地。
     *
     * @param url 视频的URL地址
     * @return 返回操作结果
     */
    @GetMapping("/test")
    public R<Void> Test(String url) {
        try {
            // 修改: 添加 %(id)s 以保留标题的UUID
            Process process = Runtime.getRuntime().exec("yt-dlp -o \"./video/%(title)s-%(id)s.%(ext)s\" \"" + url + "\"");
            printResults(process, "GBK");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }

    /**
     * 添加下载视频的接口。
     *
     * @param url 视频的URL地址
     * @return 返回视频文件的ResponseEntity
     */
//    @Async
    @GetMapping("/download")
    public void downloadVideo(@RequestParam String url, HttpServletResponse response) {
        try {
            // 使用 yt-dlp 下载视频
//            Process process = Runtime.getRuntime().exec("yt-dlp -o \"./video/%(title)s-%(id)s.%(ext)s\" \"" + url + "\"");
            Process process = Runtime.getRuntime().exec("yt-dlp -o \"./video/%(id)s.%(ext)s\" \"" + url + "\"");
            // 获取下载的文件名
            String fileName = printResults(process, "GBK");
            if (fileName != null) {
                File file = new File("./video/" + fileName);
                // 检查文件是否存在
//                if (file.exists()) {
                Resource resource = new FileSystemResource(file);
                File file1 = resource.getFile();
                String filename = resource.getFilename();
                URI uri = resource.getURI();

                // 读到流中
                // 文件的存放路径
                InputStream inputStream = new FileInputStream(file);
                response.reset();
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
                ServletOutputStream outputStream = response.getOutputStream();
                byte[] b = new byte[1024];
                int len;
                //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
                while ((len = inputStream.read(b)) > 0) {
                    outputStream.write(b, 0, len);
                }
                inputStream.close();
            } else {
                // 返回错误信息
                throw new ServiceException("无法获取下载的文件名");
            }
        } catch (IOException e) {
            // 捕获并记录 IOException 异常
            throw new RuntimeException("文件下载过程中发生IO异常: " + e.getMessage(), e);
        }
    }

    @GetMapping("/download1")
    public void downloadVideo(HttpServletResponse response) {
        try {
            // 设置yt-dlp命令，通过标准输出获取视频流
            String videoUrl = "https://www.tiktok.com/@memelord11273/video/7461105215752670494";
            ProcessBuilder processBuilder = new ProcessBuilder(
                "yt-dlp",
                // 选择下载特定的格式 mp4
                // "-f bestvideo+bestaudio",
                // 使用 -o - 将视频流输出到标准输出
                "-o", "-",
                videoUrl
            );

            Process process = processBuilder.start();

            // 获取yt-dlp的输出流（视频数据）
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            OutputStream outputStream = response.getOutputStream();

            // 设置响应头，指定文件类型和内容处理方式
//            response.setContentType("video/mp4");  // 设置合适的MIME类型
            response.setHeader("Content-Disposition", "attachment; filename=video.mp4");

            String line;
            // 获取yt-dlp的错误流，用来打印错误日志
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                // 打印错误日志
                System.err.println(errorLine);
            }

            // 将视频流直接写入HTTP响应流
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.flush();
            // 等待下载完成
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/getVideoDownloadLink")
    public R<String> getVideoDownloadLink(@RequestParam String url) {
        try {
            // 设置命令，并添加 User-Agent 头部
            ProcessBuilder processBuilder = new ProcessBuilder(
                "yt-dlp",
                "-j",   // 获取下载链接
                url
            );


            Process process = processBuilder.start();

            // 获取yt-dlp的输出流（下载链接）
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder downloadLink = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                downloadLink.append(line).append("\n");  // 拼接所有输出的链接
            }

            process.waitFor();

            JSONObject jsonObject = new JSONObject(downloadLink.toString().trim());
            System.out.println("代码行🚀jsonObject~ -> " + jsonObject);

            return R.ok(downloadLink.toString().trim());  // 返回视频下载链接

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return R.fail("Failed to get video download link.");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 打印进程的输出结果。
     *
     * @param process 进程对象
     * @param charset 字符集编码
     * @throws IOException 如果发生IO异常
     */
    public String printResults(Process process, String charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), charset));
        String line = "";
        String title = "";
        String suffix = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            // 检查是否有下载文件信息
            String[] list = {"mp4", "webm"};
            // 单个循环遍历列表并检查每个文件是否包含目标类型
            for (String type : list) {
                if (line.contains(type)) {
                    String temp = line.replaceAll("\\[download\\] ", "");
                    temp = temp.replaceAll("video\\\\", "");
                    temp = temp.replaceAll("Destination: ", "");
                    String[] fileMsg = temp.split("\\.");
                    // has already beedownloaded
                    // [download] Destination:
                    // [download]
                    title = fileMsg[0];
                    suffix = fileMsg[1];
                    break;
                }
            }
        }
        if (suffix.contains(" has already been downloaded")) {
            suffix = suffix.replaceAll(" has already been downloaded", "");
            title = title.replaceAll("video\\\\", "");
        }
        return title + "." + suffix;
    }

    /**
     * @param path     指想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:将输入流中的数据循环写入到响应输出流中，而不是一次性读取到内存
     */
    @RequestMapping("/downloadLocal")
    public void downloadLocal(String path, HttpServletResponse response) throws IOException {
        path = "E:/project/vue/videoparse/RuoYi-Vue-Plus-5.X/video/史上硬控能力最强的小可爱！#抓马综艺 #郭乐乐-7439274205868330295.mp4";
        // 读到流中
        InputStream inputStream = new FileInputStream(path);// 文件的存放路径
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File(path).getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }

}
