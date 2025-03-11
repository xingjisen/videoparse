<template>
    <a-layout-content style="min-width:1000px;width:1000px;max-width: 1024px;margin: 0 auto">
        <NuxtPage/>
        <AppMiddle v-if="!show"/>
        <FooterContent v-if="!show"/>
        <!--        padding: 0 100px; flex: 1;-->
        <!--        <NuxtLayout>-->
        <!--            哈哈-->
        <!--        </NuxtLayout>-->
    </a-layout-content>
</template>
<script lang="ts" setup>
import FooterContent from "~/layouts/FooterContent.vue";
import AppMiddle from "~/layouts/AppMiddle.vue";

const show = ref(false);
const router = useRouter();

const handleroute = () => {
    router.afterEach((to, from) => {
        switch (to.path) {
            case '/b/terms':
            case '/b/faq':
            case '/b/privacy':
            case '/b/copyright':
            case '/b/contact':
                show.value = true;
                break;
            default:
                show.value = false;
        }
    });
}
const handleInitialRoute = () => {
    const currentPath = router.currentRoute.value.path;
    switch (currentPath) {
        case "/b/terms":
        case "/b/faq":
        case "/b/privacy":
        case "/b/copyright":
        case "/b/contact":
            show.value = true;
            break;
        default:
            show.value = false;
    }
};
onMounted(() => {
    handleInitialRoute(); // 处理首次加载的路由
    handleroute(); // 注册路由守卫
})
handleroute()
</script>
