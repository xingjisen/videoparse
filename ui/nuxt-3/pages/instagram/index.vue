<template>
    <Formwork>
        <template #title>
            {{ $t('content.Instagram.title') }}
        </template>
        <template #sub-title>
            {{ $t('content.Instagram.subTitle') }}
        </template>
        <template #shows>
            <div class="shows">
                <h1>{{ $t('content.Instagram.topLeft') }}</h1>
                <p>https://www.instagram.com/p/Cx0iTd7LJlK/</p>
                <p>https://www.instagram.com/reel/CwQGTURqwg4/</p>
                <p>https://www.instagram.com/tv/Cc50Ti7rGHi</p>
                <p>https://www.instagram.com/p/B8zNe6oHwQ6/</p>
                <p>https://www.instagram.com/reel/CuT1AEkuNsa/</p>
                <p>https://www.instagram.com/p/Cx0iTd7LJlK/</p>
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
