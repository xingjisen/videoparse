<template>
    <div>
        <!--        <NuxtRouteAnnouncer/>-->
        <!--        <NuxtWelcome/>-->
        <!--        <NuxtPage/>-->

        <NuxtLayout>
            <a-layout class="layout">
                <ClientOnly>
                    <Header/>
                    <Content/>
                    <Footer/>
                </ClientOnly>
            </a-layout>
        </NuxtLayout>
    </div>
</template>
<script setup lang="ts">
import Header from "./layouts/Header.vue";
import Footer from "./layouts/Footer.vue";
import Content from "./layouts/Content.vue";
import {getInfo} from "~/api/video";


onMounted(async () => {
    await getInfo()
})
</script>

<style>
* {
    //line-height: 25px;
}

.layout {
    display: flex;
}


.layout {
    background-color: transparent;
}

.layout .ant-layout-header {
    background-color: transparent;
}

.layout .ant-layout-footer {
    background-color: transparent;
}
</style>
