<script setup lang="ts">
import {FontColorsOutlined} from "#components";

const {locale, setLocale} = useI18n();
const data = ref([
    {
        key: 'en',
        label: 'English'
    },
    {
        key: 'zh',
        label: '中文'
    }
])
const selectedLocale = ref(locale.value);
const cookie = useCookie('i18n_locale');
// 初始化读取 Cookie
if (cookie.value) {
    setLocale(cookie.value);
    selectedLocale.value = cookie.value;
}

const switchLanguage = (lang: any) => {
    if (cookie.value === lang) {
        return;
    }
    setLocale(lang);
    cookie.value = lang;
    window.location.reload();
};
</script>

<template>
    <a-dropdown>
        <a-button :icon="h(FontColorsOutlined)" shape="circle" type="link"></a-button>
        <template #overlay>
            <a-menu>
                <a-menu-item v-for="d in data" @click="switchLanguage(d.key)">
                    <a href="javascript:;">{{ d.label }}</a>
                </a-menu-item>
            </a-menu>
        </template>
    </a-dropdown>
</template>

<style scoped lang="scss">

</style>
