<template>
    <a-layout-header>
        <div>
            <!--            <div class="logo" @click="toLink('/')"></div>-->
            <!--            <p style="cursor:pointer;" @click="toLink('/')">Aurora</p>-->
            <p style="cursor:pointer;" @click="toLink('/')"><span style="color: #0d84ff !important;">Download</span>The<span style="color: #0d84ff !important;">Video</span>.online</p>
        </div>
        <div class="nav">
            <ul>
                <li>
                    <NuxtLink to="/tiktok">{{ $t('header.title.TikTok') }}</NuxtLink>
                    <!--                    <a @click="toLink('/tiktok')">TikTok</a>-->
                </li>
                <li>
                    <NuxtLink to="/instagram">{{ $t('header.title.Instagram') }}</NuxtLink>
                </li>
                <li>
                    <NuxtLink to="/twitter">{{ $t('header.title.Twitter') }}</NuxtLink>
                </li>
                <li>
                    <NuxtLink to="/douyin">{{ $t('header.title.DouYin') }}</NuxtLink>
                </li>
                <li>
                    <NuxtLink to="/kuaishou">{{ $t('header.title.KuaiShou') }}</NuxtLink>
                </li>
            </ul>
        </div>
        <div>
            <I18n/>
            <ColorMode/>
        </div>
    </a-layout-header>
</template>
<script lang="ts" setup>
import ColorMode from "~/components/ColorMode/index.vue";

const router = useRouter();
const route = useRoute();
console.log(route)

const toLink = (path: string) => {
    // console.log(path, router);
    router.push(path);
};
</script>
<style scoped lang="scss">
* {
    color: #505050 !important;
}

.layout {
    //padding: 0 50px;
    display: flex;
}

.layout .ant-layout-header {
    display: flex;
    border-bottom: 1px solid #E4E4E7;
    justify-content: space-around;

    div:nth-child(1) {
        //flex: 3;
        display: flex;
        min-width: 200px;

        .logo {
            width: 60px;
            height: 60px;
            background: url('/logos/icon.png') no-repeat center center;
            background-size: 100% 100%;
            flex: 3;
            cursor: pointer;
        }

        p {
            font-size: 1.5rem;
            font-weight: bold;
            margin: 0;
        }

    }


    .nav {
        //flex: 6;
        min-width: 500px;

        ul {
            //margin: 0 auto;
            text-align: center;
            margin: 0;

            li {
                height: 100%;
                line-height: 100%;
                display: inline-block;
                margin-right: 20px;
                cursor: pointer;
                transition: color 0.3s ease;

                &:last-child {
                    margin-right: 0;
                }

                a:hover {
                    color: #000 !important;
                }
            }
        }
    }

    div:nth-child(3) {
        min-width: 200px;
        //flex: auto;
    }
}
</style>
