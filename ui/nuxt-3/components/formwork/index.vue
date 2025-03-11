<script setup lang="ts">

import {useI18n} from 'vue-i18n';
import {parsing} from "~/api/video";
import {CopyOutlined} from "#components";

const {t: $t} = useI18n()


const videoData = ref([])
const videoDataFormats = ref([])
const link = ref('')
const open = ref(false)
const isBtn = ref(false)


const videoColumns = ref([
    {
        title: $t('content.modal.videoColumns.resolution'),
        dataIndex: 'resolution',
        key: 'resolution',
        width: 100
    },
    {
        title: $t('content.modal.videoColumns.ext'),
        dataIndex: 'ext',
        key: 'ext',
        width: 90
    },
    {
        title: $t('content.modal.videoColumns.vcodec'),
        dataIndex: 'vcodec',
        key: 'vcodec',
        width: 90
    },
    {
        title: $t('content.modal.videoColumns.acodec'),
        dataIndex: 'acodec',
        key: 'acodec',
        width: 90
    },
    {
        title: $t('content.modal.videoColumns.dynamicRange'),
        dataIndex: 'dynamic_range',
        key: 'dynamic_range',
        width: 80
    },
    /*{
        title: '总比特率',
        dataIndex: 'tbr',
        key: 'tbr',
        width: 90
    },
    {
        title: '视频比特率',
        dataIndex: 'vbr',
        key: 'vbr',
        width: 90
    },
    {
        title: '音频比特率',
        dataIndex: 'abr',
        key: 'abr',
        width: 90
    },*/
    /*{
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
    },*/
    {
        title: $t('content.modal.videoColumns.url'),
        dataIndex: 'url',
        key: 'url',
        ellipsis: true,
        width: 100,
        customCell: (record) => {
            return {
                style: {cursor: 'pointer'},
                onClick: () => copyToClipboard(record.url),
            };
        },
        customRender: ({text}) => h('div', {
            style: 'display: flex; align-items: center; gap: 8px;'
        }, [
            h(CopyOutlined, {
                style: 'color: #1890ff; margin-left: 8px;',
                onClick: (e) => {
                    e.stopPropagation();
                    copyToClipboard(text);
                }
            }),
            h('span', text)
        ])
    },
    {
        title: $t('content.modal.videoColumns.filesize'),
        dataIndex: 'filesize',
        key: 'filesize',
        customRender: ({text, record, index, column}) => {
            return `${(text / 1024 / 1024).toFixed(2)}MB`
        },
        width: 100
    },
    {
        title: $t('content.modal.videoColumns.download'),
        dataIndex: 'download',
        key: 'download',
        slots: {customRender: 'action'}, // 使用插槽自定义内容
        width: 100
    },
])
const copyToClipboard = async (text) => {
    try {
        await navigator.clipboard.writeText(text);
        message.success($t('content.modal.copySuccess'));
    } catch (err) {
        message.error($t('content.modal.copyFail') + err);
    }
};

const handleSubmit = (async () => {
    if (link.value === '') {
        return;
    }
    if (!/^(https?|ftp|http):\/\/[^\s/$.?#].[^\s]*$/.test(link.value)) {
        message.error($t('content.modal.urlError'));
        return;
    }
    isBtn.value = true
    const data = await parsing({url: link.value, type: 'tiktok'})
    if (data.data != null) {
        videoData.value = JSON.parse(data.data)
        videoDataFormats.value = videoData.value.formats.filter(f => f.resolution != null)
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


const handleDownload = async (record) => {
    console.log("record", record)
    try {
        const format = videoData.value.formats?.[0]
        if (!format) throw new Error($t('content.modal.notVideoForm'));

        // 解析鉴权信息
        const {url, http_headers, cookies} = format;
        console.log("formatformat", url, http_headers, cookies)

        // const proxyUrl = `/api/proxy/${url.replace('https://v16-webapp-prime.tiktok.com/', '')}`;
        // const encodedPath = encodeURIComponent(url.replace(/https?:\/\//, ''))
        // const proxyUrl = `/api/proxy/${encodedPath}`
        // 合并 Headers（优先使用 http_headers）
        const headers = {
            ...http_headers,
            // 强制覆盖可能冲突的头
            Host: new URL(url).hostname,
            Origin: new URL(url).origin,
            Cookie: cookies.split('; ').join('; '),
            cookies: cookies,
            'Sec-Fetch-Mode': 'no-cors',
            // Origin: 'https://www.tiktok.com',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
            // Referer: 'https://www.tiktok.com/',
            // 'Content-Disposition': 'attachment; filename="video.mp4"',
        };
        let modifyUrl = url.replace('https://v16-webapp-prime.tiktok.com/', "/tk")

        // 发送请求
        const response = await useFetch(modifyUrl, {
            // mode: 'no-cors',
            method: 'GET',
            ...headers,
            // credentials: 'include', // 允许携带 Cookie
            // credentials: 'omit', // 允许携带 Cookie
            // 流式响应（处理大文件）
            // responseType: 'stream'
        });
        // 处理二进制流
        // const blob = await response.blob();
        // const downloadUrl = window.URL.createObjectURL(blob);
        // const a = document.createElement('a');
        // a.href = downloadUrl;
        // a.download = `video_${Date.now()}.mp4`;
        // document.body.appendChild(a);
        // a.click();
        // window.URL.revokeObjectURL(downloadUrl);
        // document.body.removeChild(a);
    } catch (error) {
        // console.error('下载失败:', error);
        // alert('下载失败，请检查控制台详情');
    }
}
</script>

<template>
    <div class="body">
        <div class="heading">
            <slot name="title"></slot>
        </div>
        <div class="sub-heading">
            <slot name="sub-title"></slot>
        </div>
        <div class="input-group">
            <a-input-search
                v-model:value="link"
                :placeholder="$t('content.input.placeholder')"
                :loading="isBtn"
                @search="handleSubmit"
                size="large"
                :enter-button="$t('content.input.button')"
                style="width: 500px"
            >
                <a-button slot="enterButton">
                    Custom
                </a-button>
            </a-input-search>
        </div>
        <div class="method-sty">
            <div class="left">
                <slot name="shows">
                </slot>
            </div>
            <div class="right">
                <ul>
                    <li>
                        <span>1</span>
                        <p>{{ $t('content.rightTop.one') }}</p>
                    </li>
                    <li>
                        <span>2</span>
                        <p>{{ $t('content.rightTop.two') }}</p>
                    </li>
                    <li>
                        <span>3</span>
                        <p>{{ $t('content.rightTop.three') }}</p>
                    </li>
                </ul>
            </div>
        </div>
        <div class="advantage">
            <ul>
                <li>
                    <p>{{ $t('content.desc.one') }}</p>
                    <p>{{ $t('content.desc.oneSub') }}</p>
                </li>
                <li>
                    <p>{{ $t('content.desc.two') }}</p>
                    <p>{{ $t('content.desc.twoSub') }}</p>
                </li>
                <li>
                    <p>{{ $t('content.desc.three') }}</p>
                    <p>{{ $t('content.desc.threeSub') }}</p>
                </li>
                <li>
                    <p>{{ $t('content.desc.four') }}</p>
                    <p>{{ $t('content.desc.fourSub') }}</p>
                </li>
            </ul>
        </div>


        <a-modal v-model:open="open" :title="$t('content.modal.title')" width="70%" maxWidth="80%" :footer=false>
            <div style="display: flex;justify-content: space-around">
                <div style="width: 30%;">
                    <div class="left-content">
                        <div style="font-weight: bold;text-align: center">{{ $t('content.modal.subTitle') }}</div>
                        <div @click="copyToClipboard(videoData.title)" :title="videoData.title" class="ellipsis-cell">{{ videoData.title }}</div>
                    </div>
                    <div style="width: 100%;text-align: center">
                        <div style="font-weight: bold;margin: 10px 0;">{{ $t('content.modal.cover') }}</div>
                        <img :src="videoData.thumbnail" height="100%" style="display: inline-block;height: 300px;margin: 0 auto;" alt="" srcset="">
                    </div>
                    <!--                    <button @click="handleDownload">下载视频（最佳质量）</button>-->
                </div>
                <div style="width: 70%;">
                    <a-table :scroll="{y:430}" :pagination="false" :dataSource="videoDataFormats" :columns="videoColumns" bordered style="width:100%;">
                        <template #action="{ record }">
                            <a-button size="small" type="primary" @click="handleDownload(record)">{{ $t('content.modal.download') }}</a-button>
                        </template>
                    </a-table>
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


    }

    .method-sty {
        margin: 30px 0;
        display: flex;
        justify-content: space-evenly;

        .left {
            //margin-top: 50px;

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
            height: 183px;

            ul {
                width: 120%;


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
                        color: #fff;
                        background-color: red;
                        border-radius: 40px;
                        margin-top: -5px;

                        &:nth-child(1) {
                            background-color: #3864ED;
                        }
                    }

                    p {
                        margin: 0 0 0 10px;
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
                list-style: none;

                p:nth-child(1) {
                    font-weight: bold;
                    font-size: 1.2rem;
                    margin: 10px 0;
                }

                p:nth-child(2) {
                    font-size: 0.9rem;
                    line-height: 20px;
                    margin: 10px 0;
                }
            }
        }
    }
}

</style>

<style scoped>
::v-deep .ant-input {
    background: transparent;
}

::v-deep .ant-card-body {
    width: 100%;
    padding: 0;
}

.left-content {
    width: 100%;
    text-align: center;

    .ellipsis-cell {
        white-space: nowrap; /* 防止换行 */
        overflow: hidden; /* 隐藏超出部分 */
        text-overflow: ellipsis; /* 显示省略号 */
        width: 100%; /* 设置最大宽度 */
        cursor: pointer;
    }
}
</style>

