<script setup lang="ts">
// definePageMeta({
//     layout: false, // 指定使用空布局
// });
</script>

<template>
    <div>
        <div class="title">
            {{ $t('terms.title') }}
        </div>
        <p>{{ $t('terms.introduction') }}</p>
        <p>{{ $t('terms.lastUpdated') }}</p>
        <div class="secondary_headings">{{ $t('terms.sections.title') }}</div>
        <ul class="list">
            <li>{{ $t('terms.sections.content.one') }}</li>
            <li>{{ $t('terms.sections.content.two') }}</li>
            <li>{{ $t('terms.sections.content.three') }}</li>
            <li>{{ $t('terms.sections.content.four') }}</li>
            <li>{{ $t('terms.sections.content.five') }}</li>
            <li>{{ $t('terms.sections.content.six') }}</li>
            <li>{{ $t('terms.sections.content.seven') }}</li>
            <li>{{ $t('terms.sections.content.eight') }}</li>
        </ul>
    </div>
</template>

<style scoped lang="scss">
.title {
    margin: 50px 0 20px 0;
}

.secondary_headings {
    font-size: 1.2rem;
    font-weight: bold;
    margin: 40px 0 20px 0;
}

.list {
    color: #666;
    line-height: 25px;
    margin: 0 0 50px 0;

    li {
        margin: 10px 0;
    }
}
</style>
