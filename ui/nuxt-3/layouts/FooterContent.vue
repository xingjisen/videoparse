<template>
    <div>
        <a-collapse :bordered="false" v-model="activeKey"
                    style="background:transparent">
            <a-collapse-panel v-for="(help,index) in helpDoc" :key="String(index+1)" :header="help.label">
                <p>{{ help.content }}</p>
            </a-collapse-panel>
        </a-collapse>
    </div>
</template>
<script setup>
import {useI18n} from 'vue-i18n';

const {t: $t} = useI18n()
const activeKey = ref(['1', '2', '3', '4', '5', '6'])
const helpDoc = ref([
    {
        key: '1',
        label: $t('FooterContent.oneTit'),
        content: $t('FooterContent.oneSub'),
    },
    {
        key: '2',
        label: $t('FooterContent.twoTit'),
        content: $t('FooterContent.twoSub')
    },
    {
        key: '3',
        label: $t('FooterContent.threeTit'),
        content: $t('FooterContent.threeSub')
    },
    {
        key: '4',
        label: $t('FooterContent.fourTit'),
        content: $t('FooterContent.fourSub')
    },
    {
        key: '5',
        label: $t('FooterContent.fiveTit'),
        content: $t('FooterContent.fiveSub')
    },
    {
        key: '6',
        label: $t('FooterContent.sixTit'),
        content: $t('FooterContent.sixSub')
    }
])
</script>
<style scoped lang="scss">

</style>
