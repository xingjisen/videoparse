<template>
    <Formwork>
        <template #title>
            {{ $t('content.DouYin.title') }}
        </template>
        <template #sub-title>
            {{ $t('content.DouYin.subTitle') }}
        </template>
        <template #shows>
            <div class="shows">
                <h1>{{ $t('content.DouYin.topLeft') }}</h1>
                <p>https://v.douyin.com/idLcdTBj/</p>
                <p>https://www.douyin.com/video/7006255072720047373</p>
                <p>https://m.tiktok.com/v/7269493447239683371.html</p>
                <p>https://www.tiktok.com/@mobolistcom/video/7256790112627985665</p>
            </div>
        </template>
    </Formwork>

</template>
<script setup>
import Formwork from '~/components/formwork/index.vue'
</script>
<style scoped>
.shows {
    h1 {
        font-weight: bold;
        font-size: 1.5rem;
    }

    h2 {
        margin-top: 20px;
        font-size: 1.2rem;
    }
}
</style>
