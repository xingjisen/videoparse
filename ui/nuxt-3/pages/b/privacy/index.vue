<script setup lang="ts">
// definePageMeta({
//     layout: false, // 指定使用空布局
// });
</script>

<template>
    <div>
        <div class="title">
            {{ $t('privacy.title') }}
        </div>
        <p>{{ $t('privacy.lastUpdated') }}</p>
        <div class="secondary_headings">{{ $t('privacy.sections.overview.title') }}</div>
        <ul class="list">
            <li>{{ $t('privacy.sections.overview.content.one') }}</li>
            <li>{{ $t('privacy.sections.overview.content.two') }}</li>
            <li>{{ $t('privacy.sections.overview.content.three') }}</li>
        </ul>
        <div class="secondary_headings">{{ $t('privacy.sections.autoCollectedInfo.title') }}</div>
        <ul class="list">
            <li><span style="font-weight: bold">DownloadTheVideo.online</span>{{ $t('privacy.sections.autoCollectedInfo.content.intro') }}</li>
            <li>{{ $t('privacy.sections.autoCollectedInfo.content.ip') }}</li>
            <li>{{ $t('privacy.sections.autoCollectedInfo.content.browserType') }}</li>
            <li>{{ $t('privacy.sections.autoCollectedInfo.content.dateTime') }}</li>
            <li>{{ $t('privacy.sections.autoCollectedInfo.content.referrer') }}</li>
            <li>{{ $t('privacy.sections.autoCollectedInfo.content.language') }}</li>
        </ul>
        <div class="secondary_headings">{{ $t('privacy.sections.cookie.title') }}</div>
        <ul class="list">
            <li>{{ $t('privacy.sections.cookie.content.one') }}
            </li>
            <li>{{ $t('privacy.sections.cookie.content.two.text') }}
                <a
                    :href="$t('privacy.sections.cookie.content.two.link.url')">
                    {{ $t('privacy.sections.cookie.content.two.link.text') }}
                </a>
            </li>
            <li style="display: none">我们通过 PropellerAds 提供广告，PropellerAds 使用 cookie 来投放相关广告，PropellerAds cookie
                不会收集个人身份信息，您可以查看隐私政策页面了解更多信息，也可以使用此退出页面选择退出。
            </li>
        </ul>
        <div class="secondary_headings">{{ $t('privacy.sections.links.title') }}</div>
        <ul class="list">
            <li>{{ $t('privacy.sections.links.content.one') }}</li>
        </ul>
        <div class="secondary_headings">{{ $t('privacy.sections.policyChanges.title') }}</div>
        <ul class="list">
            <li><span style="font-weight: bold">DownloadTheVideo.online</span>{{ $t('privacy.sections.policyChanges.content.one') }}
            </li>
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
    line-height: 25px;
    margin: 0 0 50px 0;

    li {
        margin: 20px 0;
    }
}
</style>
