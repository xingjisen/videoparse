package org.dromara.video.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.web.core.BaseController;
import org.dromara.video.domain.bo.InfoBo;
import org.dromara.video.service.VideoParseService;
import org.dromara.video.service.impl.InfoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频解析
 *
 * @author Jason
 * @PACKAGE_NAME com.video
 * @Description
 * @date 2025-02-18 22:00:29
 */
@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoParseController extends BaseController {
    private final VideoParseService videoParseService;
    private final InfoServiceImpl infoService;

    @GetMapping("/getVideoDownloadData")
    public R<String> getVideoDownloadData(@RequestParam("url") String url, @RequestParam("type") String type) {
        return R.ok("success", videoParseService.getVideoDownloadData(url, type));
    }


    /**
     * 获取用户访问信息
     */
    @GetMapping("/info")
    public Map<String, Object> getUserInfo(HttpServletRequest request) {
        Map<String, Object> info = new HashMap<>();
        InfoBo infoBo = new InfoBo();
        // 1. 获取客户端IP地址（兼容反向代理场景）
        String clientIp = getClientIp(request);
        info.put("ip", clientIp);
        infoBo.setIp(clientIp);

        // 2. 获取设备信息
        String userAgent = request.getHeader("User-Agent");
        info.put("userAgent", userAgent);
        infoBo.setUseragent(userAgent);
//        info.put("deviceInfo", parseDeviceInfo(userAgent));

        // 3. 获取地理位置（需第三方API，此处返回IP供后续处理）
        info.put("geoLocation", "需通过IP调用第三方API获取，例如：https://ipapi.co/" + clientIp + "/json/");
        String body = HttpRequest.get("https://ipapi.co/" + clientIp + "/json/").execute().body();
        infoBo.setGetlocation(body);

        // 4. 其他请求头信息（可选）
        info.put("headers", getAllHeaders(request));
        String string = JSONUtil.parse(info).toString();
        infoBo.setHeaders(string);
        infoBo.setReqTime(new Date(System.currentTimeMillis()));
        infoService.insertByBo(infoBo);
        return info;
    }

    /**
     * 获取真实客户端IP（支持反向代理如Nginx）
     *
     * @param request
     * @return
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.split(",")[0]; // 处理多级代理情况
    }

    /**
     * 解析设备信息（需引入 UserAgentUtils 依赖）
     *
     * @param userAgent
     * @return
     */
    private Map<String, String> parseDeviceInfo(String userAgent) {
        Map<String, String> device = new HashMap<>();
        try {
            // 添加依赖：com.github.ua-parser/uap-java
            ua_parser.Client client = new ua_parser.Parser().parse(userAgent);
            device.put("os", client.os.family);
            device.put("deviceType", client.device.family);
            device.put("browser", client.userAgent.family);
            device.put("browserVersion", client.userAgent.major);
        } catch (Exception e) {
            device.put("error", "UserAgent解析失败");
        }
        return device;
    }

    /**
     * 获取所有请求头
     *
     * @param request
     * @return
     */
    private Map<String, String> getAllHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
}
