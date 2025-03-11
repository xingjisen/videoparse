// https://nuxt.com/docs/api/configuration/nuxt-config


import * as path from "path";

console.log('en.json path:', path.resolve(__dirname, 'locales/en.json'))
console.log('zh.json path:', path.resolve(__dirname, 'locales/zh.json'))
export default defineNuxtConfig({
    app: {
        pageTransition: {
            name: "page",
            mode: "out-in"
        },
        layoutTransition: {
            name: "layout",
            mode: "out-in"
        },
        head: {
            script: [
                // 加载 gtag.js
                {
                    src: 'https://www.googletagmanager.com/gtag/js?id=G-XXRJ4T92RE',
                    async: true,
                },
                // 初始化 gtag
                {
                    innerHTML: `
                        window.dataLayer = window.dataLayer || [];
                        function gtag(){dataLayer.push(arguments);}
                        gtag('js', new Date());
                        gtag('config', 'G-XXRJ4T92RE');
                    `,
                    type: 'text/javascript',
                },


                // google 广告
                {
                    src: 'https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-2636364275828056',
                    async: true,
                    crossorigin: 'anonymous',
                },
            ],
        }
    },
    compatibilityDate: '2024-11-01',
    modules: [
        '@ant-design-vue/nuxt',
        '@nuxtjs/color-mode',
        '@nuxtjs/i18n'
    ],
    i18n: {
        locales: [

            {
                code: 'zh',
                name: '中文',
                file: 'zh.json'
            },
            {
                code: 'en',
                name: 'English',
                file: 'en.json'
            }

        ],
        lazy: true,
        langDir: '../locales',
        defaultLocale: 'en',
        strategy: 'no_prefix',
        detectBrowserLanguage: {
            useCookie: true,
            cookieKey: 'i18n_redirected',
            redirectOn: 'root'
        }
    },
    // 主题颜色切换
    colorMode: {
        preference: 'light', // $colorMode.preference 的默认值
        fallback: 'light', // fallback 值（如果未找到系统首选项）
        hid: 'nuxt-color-mode-script',
        globalName: '__NUXT_COLOR_MODE__',
        componentName: 'ColorScheme',
        classPrefix: '',
        classSuffix: '-mode',
        storage: 'localStorage',
        storageKey: 'nuxt-color-mode'
    },
    server: {
        gzip: true
    },
    build: {
        optimizeCSS: true, // 默认使用cssnano压缩
        transpile: ['ant-design-vue'], // 确保组件正确编译
        // 生产环境 移除 console.log
        terser: {
            terserOptions: {
                compress: {
                    drop_console: true,
                    pure_funcs: ['console.error', 'console.warn'], // 保留 error 和 warn
                },
            },
        }
    },
    antd: {
        resolveIcons: true,  // 启用图标库支持 ‌:ml-citation{ref="2,5" data="citationList"}
        importStyle: 'css'   // 指定样式加载方式 ‌:ml-citation{ref="5,8" data="citationList"}
    },
    css: ['ant-design-vue/dist/reset.css'],
    runtimeConfig: {
        public: {
            API_BASE_DEV: '/dev-api',
            API_BASE_PROD: '/prod-api'
        }
    },
    devtools: {enabled: true},
    optimizedeps: {
        esbuildoptions: {
            target: 'es2020'
        }
    },
    extractCSS: 'production',
    nitro: {
        devProxy: {
            '/tk': {
                target: "https://v16-webapp-prime.tiktok.com/",
                changeOrigin: true,
                rewrite: (path: any) => {
                    console.log(123456, path)
                    return path.replace(/^\/tk/, "")
                },
                proxyTimeout: 30000,
                // cache: false,
                // headers: {
                //     Referer: "https://www.tiktok.com/",
                // },
            },
            "/dev-api": {
                target: "http://localhost:8080",
                changeOrigin: true,
                rewrite: (path: any) => {
                    console.log(123456, path)
                    return path.replace(/^\/dev-api/, "")
                },
            },
        }
    }
})
