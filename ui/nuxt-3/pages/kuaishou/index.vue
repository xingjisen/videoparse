<template>
    <Formwork>
        <template #title>
            {{ $t('content.KuaiShou.title') }}
        </template>
        <template #sub-title>
            {{ $t('content.KuaiShou.subTitle') }}
        </template>
        <template #shows>
            <div class="shows">
                <h1>{{ $t('content.KuaiShou.topLeft') }}</h1>
                <h2>{{ $t('content.KuaiShou.bottomLeft') }}</h2>
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
