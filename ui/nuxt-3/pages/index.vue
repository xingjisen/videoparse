<template>
    <Formwork>
        <template #title>
            {{ $t('content.title') }}
        </template>
        <template #sub-title>
            {{ $t('content.subTitle') }}
        </template>
        <template #shows>
            <div class="shows">
                <h1>{{ $t('content.topLeft') }}</h1>
                <h2>{{ $t('content.bottomLeft') }}</h2>
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
