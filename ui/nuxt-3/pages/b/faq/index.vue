<script setup lang="ts">

</script>

<template>
    <div>
        <div class="title">{{ $t('faq.title') }}</div>
        <div>{{ $t('faq.titleSub') }}</div>
        <ul class="list">
            <li>
                <p>{{ $t('faq.content.oneTitle') }}</p>
                <p>{{ $t('faq.content.oneContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.twoTitle') }}</p>
                <p>{{ $t('faq.content.twoContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.threeTitle') }}</p>
                <p>{{ $t('faq.content.threeContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.fourTitle') }}</p>
                <p>{{ $t('faq.content.fourContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.fiveTitle') }}</p>
                <p>{{ $t('faq.content.fiveContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.sixTitle') }}</p>
                <p>{{ $t('faq.content.sixContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.sevenTitle') }}</p>
                <p>{{ $t('faq.content.sevenContent') }}</p>
            </li>
            <li>
                <p>{{ $t('faq.content.eightTitle') }}</p>
                <p>{{ $t('faq.content.eightContent.one') }}
                    <router-link to="/b/terms">{{ $t('faq.content.eightContent.two') }}</router-link>
                    {{ $t('faq.content.eightContent.three') }}
                </p>
            </li>
        </ul>

    </div>
</template>

<style scoped lang="scss">
.title {
    margin-top: 50px;
}

.list {
    margin: 20px 0;
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    list-style: none;

    li {
        width: 48%;
        line-height: 25px;
        margin: 20px 0;

        p:nth-child(1) {
            font-weight: bold;
        }

        p:nth-child(2) {
            color: #656565;
        }
    }
}
</style>
