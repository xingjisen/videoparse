<script setup lang="ts">

import {parsing} from "~~/api/video";
import {CopyOutlined} from "#components";

const videoData = ref([])
const videoColumns = ref([
  {
    title: '分辨率',
    dataIndex: 'resolution',
    key: 'resolution',
    width: 100
  },
  {
    title: '格式',
    dataIndex: 'ext',
    key: 'ext',
    width: 80
  },
  {
    title: 'cookies',
    dataIndex: 'cookies',
    key: 'cookies',
    ellipsis: true,
    width: 100,
    customCell: (record) => {
      return {
        style: {cursor: 'pointer'},
        onClick: () => copyToClipboard(record.cookies),
      };
    },
    customRender: ({text}) => h('div', {
      style: 'display: flex; align-items: center; gap: 8px;'
    }, [
      h('span', text),
      h(CopyOutlined, {
        style: 'color: #1890ff; margin-left: 8px;',
        onClick: (e) => {
          e.stopPropagation();
          copyToClipboard(text);
        }
      })
    ])
  },
  {
    title: '地址',
    dataIndex: 'url',
    key: 'url',
    ellipsis: true,
    customCell: (record) => {
      return {
        style: {cursor: 'pointer'},
        onClick: () => copyToClipboard(record.url),
      };
    },
    customRender: ({text}) => h('div', {
      style: 'display: flex; align-items: center; gap: 8px;'
    }, [
      h('span', text),
      h(CopyOutlined, {
        style: 'color: #1890ff; margin-left: 8px;',
        onClick: (e) => {
          e.stopPropagation();
          copyToClipboard(text);
        }
      })
    ])
  },
  {
    title: '文件大小',
    dataIndex: 'filesize',
    key: 'filesize',
    customRender: ({text, record, index, column}) => {
      return `${(text / 1024 / 1024).toFixed(2)}MB`
    },
  },
])
const copyToClipboard = async (text) => {
  try {
    await navigator.clipboard.writeText(text);
    message.success('已复制到剪切板');
  } catch (err) {
    message.error('复制失败: ' + err);
  }
};

const link = ref('https://www.tiktok.com/@bombo_ai/video/7451733555887164694?is_from_webapp=1&sender_device=pc&web_id=7473494785737901614')
const open = ref(false)
const isBtn = ref(false)
const handleSubmit = (async () => {
  isBtn.value = true
  //TODO 解析视频根据返回结果打开视频分辨率选择对话框
  const data = await parsing({url: link.value, type: 'tiktok'})
  if (data.data != null) {
    videoData.value = JSON.parse(data.data)
    open.value = true
  }
  console.log("videoData.value", videoData.value)
  isBtn.value = false
  // videoData.value = [
  //   {
  //     name: '视频',
  //     icon: 'i-simple-icons-youtube',
  //     url: 'https://www.youtube.com/watch?v=dQw4w9WgXcQ',
  //   },
  //   {
  //     name: '音频',
  //     icon: 'i-simple-icons-spotify',
  //     url: 'https://open.spotify.com/track/4uZQJYjQYz9Y9Y0X0Q0Q0Q?si=0a0a0a0a0a0a0a0a',
  //   }]
  // 下载视频流
  /*try {
    // 使用 $fetch 发送请求
    const {data} = await useFetch('http://localhost:8080/video/download1?url=' + link.value, {
      method: 'GET',
      headers: {
        'Accept': 'video/mp4',
      },
      responseType: 'blob',
    });
    console.log("aaa", data.value, URL)
    const videoUrl = URL.createObjectURL(data.value);

    const linkElement = document.createElement('a');
    linkElement.href = videoUrl;
    linkElement.download = 'video.mp4';
    document.body.appendChild(linkElement);
    linkElement.click();

    // 清理
    document.body.removeChild(linkElement);
    URL.revokeObjectURL(videoUrl);

  } catch (error) {
    console.error("下载视频时出错", error);
  }*/
})


const handleDownload = async () => {
  try {
    const format = videoData.value.formats?.[0]
    if (!format) throw new Error('未找到可用视频格式');

    // 解析鉴权信息
    const {url, http_headers, cookies} = format;
    console.log("formatformat", url, http_headers, cookies)

    const proxyUrl = `http://localhost/proxy/${url}`;
    // 合并 Headers（优先使用 http_headers）
    const headers = {
      ...http_headers,
      Cookie: cookies.split('; ').join('; '),
      Origin: 'https://www.tiktok.com',
      'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
      Referer: 'https://www.tiktok.com/',
      'Content-Disposition': 'attachment; filename="video.mp4"',
    };

    console.log("headers", headers)

    // 发送请求
    const response = await useFetch(proxyUrl, {
      // mode: 'no-cors',
      method: 'GET',
      ...headers,
      // credentials: 'include', // 允许携带 Cookie
      credentials: 'omit', // 允许携带 Cookie
    });

    // 处理二进制流
    const blob = await response.blob();
    const downloadUrl = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = downloadUrl;
    a.download = `video_${Date.now()}.mp4`;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(downloadUrl);
    document.body.removeChild(a);
  } catch (error) {
    console.error('下载失败:', error);
    alert('下载失败，请检查控制台详情');
  }
}
</script>

<template>
  <div class="body">
    <div class="heading">从所有平台下载视频/提取图像</div>
    <div class="sub-heading">免费、快速、无需登录，支持100多个平台</div>
    <div class="input-group">
      <UButtonGroup orientation="horizontal">
        <UInput class="input-sty" v-model="link" size="xl" color="neutral" variant="outline" placeholder="请粘贴视频/图像链接"/>
        <UButton class="input-btn" :style="link?'cursor: pointer;':''" :disabled="!link" :loading="isBtn" color="neutral" variant="subtle" @click="handleSubmit" label="提交"/>
      </UButtonGroup>
    </div>
    <div class="method-sty">
      <div class="left">
        <h1>最好用的视频/图片下载器</h1>
        <h2>没有限制,免登录使用</h2>
      </div>
      <div class="right">
        <ul>
          <li>
            <span>1</span>
            <p>复制可分享的视频/图集链接</p>
          </li>
          <li>
            <span>2</span>
            <p>将链接粘贴到上面的输入框中</p>
          </li>
          <li>
            <span>3</span>
            <p>点击视频/音频/图片下载按钮</p>
          </li>
        </ul>
      </div>
    </div>
    <div class="advantage">
      <ul>
        <li>
          <p>免费使用</p>
          <p>解析和下载视频、音频、图片等文件无需任何费用。</p>
        </li>
        <li>
          <p>无次数限制</p>
          <p>本站的解析和下载服务没有次数限制，您可以根据需要随意使用。</p>
        </li>
        <li>
          <p>无需注册</p>
          <p>视频解析和下载可以直接使用，无需注册帐户或提供个人信息，充分简化了流程并尊重用户隐私。</p>
        </li>
        <li>
          <p>高清音视频</p>
          <p>默认下载最高质量的视频，并根据情况提供HD、1080P、2K、4K等选择。</p>
        </li>
      </ul>
    </div>


    <a-modal v-model:open="open" title="视频列表" width="70%" maxWidth="80%" :footer=false>
      <div style="display: flex;justify-content: space-around">
        <div style="width: 48%;">
          <div><span style="font-weight: bold;">视频/图像标题: </span>{{ videoData.title }}</div>
          <a-card title="封面" style="width: 100%;margin-top: 20px">
            <img :src="videoData.thumbnail" alt="" srcset="">
          </a-card>
          <button @click="handleDownload">下载视频（最佳质量）</button>
        </div>
        <div style="width: 48%;">
          <a-table :pagination="false" :dataSource="videoData.formats" :columns="videoColumns" bordered style="width:100%;"/>
        </div>
      </div>
    </a-modal>
  </div>
</template>

<style scoped lang="scss">
.body {
  display: flex;
  flex-direction: column;

  .heading {
    width: 100%;
    margin: 30px 0 20px 0;
    text-align: center;
    color: #3864ED;
    font-weight: bold;
    font-size: 2rem;
  }

  .sub-heading {
    width: 100%;
    margin: 0 0 30px 0;
    text-align: center;
    font-weight: bold;
  }

  .input-group {
    margin: 0 auto;

    .input-sty {
      width: 500px;
    }

    .input-btn {
      background-color: #3864ED;
      color: white;
      width: 100px !important;
      text-align: center !important;

      .truncate {
        text-align: center;
        display: inline-block;
        width: 100%;
      }
    }
  }

  .method-sty {
    margin: 30px 0;
    display: flex;
    justify-content: space-between;

    .left {
      margin-top: 50px;

      h1 {
        font-weight: bold;
        font-size: 1.5rem;
      }

      h2 {
        margin-top: 20px;
        font-size: 1.2rem;
      }
    }

    .right {
      background-color: rgba(229, 240, 252, 0.42);
      padding: 0 150px 0 20px;

      ul {
        width: 150%;

        li {
          width: 100%;
          margin: 20px 0 0 0;
          border-bottom: 1px solid #d5d5d5;

          &:last-child {
            border-bottom: none;
          }

          display: flex;

          span {
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            font-size: 1.1rem;
            color: white;
            background-color: red;
            border-radius: 40px;
            margin-top: -5px;

            &:nth-child(1) {
              background-color: #3864ED;
            }
          }

          p {
            margin-left: 10px;
            padding: 0 0 20px 0;
          }
        }
      }
    }
  }

  .advantage {
    margin: 30px 0;

    ul {
      display: flex;
      justify-content: space-between;
      width: 100%;
      flex-wrap: wrap;

      li {
        width: 40%;

        p:nth-child(1) {
          font-weight: bold;
          font-size: 1.2rem;
          margin: 10px 0;
        }

        p:nth-child(2) {
          font-size: 0.9rem;
          margin: 10px 0;
        }
      }
    }
  }
}
</style>

