<template>
    <div class="flex">
        <div>
            <div class="title">{{ $t('contact.title') }}</div>
            <div>{{ $t('contact.titleSub') }}</div>
        </div>
        <div>
            <!-- modify this form HTML and place wherever you want your form -->
            <form
                action="https://formspree.io/f/xoveavbq"
                method="POST"
            >
                <a-input style="margin-bottom: 20px" type="email" :placeholder="$t('contact.email')" name="email"/>
                <a-input style="margin-bottom: 20px" type="input" :placeholder="$t('contact.theme')" name="theme"/>
                <a-input style="margin-bottom: 20px" type="url" :placeholder="$t('contact.url')" name="url"/>
                <a-input style="margin-bottom: 20px" type="text" :placeholder="$t('contact.msg')" name="message"/>
                <a-button type="primary" html-type="submit">{{ $t('contact.button') }}</a-button>
            </form>
            <div style="margin-top: 20px">{{ $t('contact.provider.one') }} <a href="https://formspree.io/">{{ $t('contact.provider.two') }}</a> {{ $t('contact.provider.three') }}</div>
        </div>
    </div>
</template>
<script setup lang="ts">
</script>

<style scoped>
.flex {
    margin: 50px 0;
    display: flex;
    justify-content: space-around;

    & > div:nth-child(1) {
        width: 30%;
    }

    & > div:nth-child(2) {
        width: 40%;
    }
}
</style>

