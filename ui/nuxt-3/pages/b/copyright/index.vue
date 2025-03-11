<script setup lang="ts">
// definePageMeta({
//     layout: false, // 指定使用空布局
// });
</script>

<template>
    <div>
        <div class="title">
            {{ $t('copyright.title') }}
        </div>
        <p>{{ $t('copyright.lastUpdated') }}</p>
        <ul class="list">
            <li>{{ $t('copyright.content.points.one') }}</li>
            <li>{{ $t('copyright.content.points.two.text') }}
                <router-link to="/b/terms">{{ $t('copyright.content.points.two.title') }}</router-link>
                {{ $t('copyright.content.points.two.symbol') }}
            </li>
            <li>{{ $t('copyright.content.points.three') }}</li>
            <li>{{ $t('copyright.content.points.four') }}</li>
            <li>{{ $t('copyright.content.points.five') }}</li>
            <li>{{ $t('copyright.content.points.six.text') }} <a :href="`mailto:xingjisen66@gmail.com`">xingjisen66@gmail.com</a>{{
                    $t('copyright.content.points.six.note')
                }}
            </li>
            <li>{{ $t('copyright.content.points.seven') }}</li>
            <ul style="color: #666">
                <li>{{ $t('copyright.content.points.requirements.one') }}</li>
                <li>{{ $t('copyright.content.points.requirements.two') }}</li>
                <li>{{ $t('copyright.content.points.requirements.three') }}</li>
                <li>{{ $t('copyright.content.points.requirements.four') }}</li>
                <li>{{ $t('copyright.content.points.requirements.five') }}</li>
                <li>{{ $t('copyright.content.points.requirements.six') }}</li>
                <li>{{ $t('copyright.content.points.requirements.seven') }}</li>
            </ul>
            <li>{{ $t('copyright.content.points.eight') }}</li>
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
    list-style: none;
    color: #666;
    line-height: 30px;
    margin: 0 0 50px 0;

    li {
        margin: 20px 0;
    }
}
</style>
