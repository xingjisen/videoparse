<script setup lang="ts">

import {BulbOutlined, EyeOutlined} from "#components";

const colorMode = useColorMode()


// 切换模式
const setColorMode = () => {
    colorMode.value = colorMode.value === 'sepia' ? 'light' : 'sepia'
    colorMode.preference = colorMode.preference === 'sepia' ? 'light' : 'sepia'
}

// 判断是否支持 startViewTransition API
const enableTransitions = () =>
    'startViewTransition' in document &&
    window.matchMedia('(prefers-reduced-motion: no-preference)').matches

// 切换动画
async function toggleDark({clientX: x, clientY: y}: MouseEvent) {
    const isSepia = colorMode.value === 'sepia'

    if (!enableTransitions()) {
        setColorMode()
        return
    }

    const clipPath = [
        `circle(0px at ${x}px ${y}px)`,
        `circle(${Math.hypot(
            Math.max(x, innerWidth - x),
            Math.max(y, innerHeight - y)
        )}px at ${x}px ${y}px)`
    ]

    await document.startViewTransition(async () => {
        setColorMode()
        await nextTick()
    }).ready

    document.documentElement.animate(
        {clipPath: !isSepia ? clipPath.reverse() : clipPath},
        {
            duration: 300,
            easing: 'ease-in',
            pseudoElement: `::view-transition-${!isSepia ? 'old' : 'new'}(root)`
        }
    )
}
</script>

<template>
    <a-tooltip placement="bottom">
        <template #title>
            <span>{{ `${$colorMode.value === 'sepia' ? $t('header.mode.light') : $t('header.mode.sepia')}` }}</span>
        </template>
        <a-button v-if="$colorMode.value === 'sepia'" circle type="text" @click="toggleDark" :icon="h(BulbOutlined)"/>
        <a-button v-if="$colorMode.value === 'light'" circle type="text" @click="toggleDark" :icon="h(EyeOutlined)"/>
        <!--        <a-button circle type="text" @click="$colorMode.preference='dark'" :icon="h(EyeOutlined)"/>-->
        <!--                <a-button v-if="$colorMode.value === 'dark'" circle type="text" @click="toggleDark" :icon="h(EyeOutlined)"/>-->
        <!--        <a-button v-if="$colorMode.value === 'system'" circle type="text" @click="toggleDark" :icon="h(EyeOutlined)"/>-->
    </a-tooltip>
</template>

<style>
body {
    background-color: #fff;
    color: rgba(0, 0, 0, 0.8);
}

.dark-mode body {
    background-color: #707a83;
    color: #ebf4f1;
}

.sepia-mode body {
    background-color: #f1e7d0;
    color: #433422;
}
</style>
