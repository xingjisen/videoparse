<template>
    <div>
        <div class="title">{{ $t('AppMiddle.title') }}</div>
        <div class="body">
            <div class="children" v-for="videoIcon in videoIcons">
                <img :src="`/img/videoIcon/${videoIcon.href}`" alt="">
                <p>{{ videoIcon.title }}</p>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
import {useI18n} from 'vue-i18n';

const {t: $t} = useI18n()
const videoIcons = ref([
    {
        title: $t('AppMiddle.Facebook'),
        href: 'facebook.svg'
    },
    {
        title: $t('AppMiddle.Twitter'),
        href: 'twitter.svg'
    },
    {
        title: $t('AppMiddle.Instagram'),
        href: 'instagram.svg'
    },
    {
        title: $t('AppMiddle.Telegram'),
        href: 'telegram.svg'
    },
    {
        title: $t('AppMiddle.TikTok'),
        href: 'tiktok.svg'
    },
    {
        title: $t('AppMiddle.9GAG'),
        href: '9gag.svg'
    },
    {title: $t('AppMiddle.Mashable'), href: 'mashable.svg'},
    {title: $t('AppMiddle.Linkedin'), href: 'linkedin.svg'},
    {title: $t('AppMiddle.Likee'), href: 'likee.svg'},
    {title: $t('AppMiddle.Kwai'), href: 'kwai.svg'},
    {title: $t('AppMiddle.Imgur'), href: 'imgur.svg'},
    {title: $t('AppMiddle.Imdb'), href: 'imdb.svg'},
    {title: $t('AppMiddle.Ifunny'), href: 'ifunny.svg'},
    {title: $t('AppMiddle.Gaana'), href: 'gaana.svg'},
    {title: $t('AppMiddle.Flickr'), href: 'flickr.svg'},
    {title: $t('AppMiddle.Febspot'), href: 'febspot.svg'},
    {title: $t('AppMiddle.Espn'), href: 'espn.svg'},
    {title: $t('AppMiddle.Akillitv'), href: 'akillitv.svg'},
    {title: $t('AppMiddle.Bandcamp'), href: 'bandcamp.svg'},
    {title: $t('AppMiddle.Bitchute'), href: 'bitchute.svg'},
    {title: $t('AppMiddle.Blogger'), href: 'blogger.svg'},
    {title: $t('AppMiddle.Blutv'), href: 'blutv.svg'},
    {title: $t('AppMiddle.Buzzfeed'), href: 'buzzfeed.svg'},
    {title: $t('AppMiddle.Dailymotion'), href: 'dailymotion.svg'},
])


</script>
<style lang="scss">
.title {
    font-weight: bold;
    font-size: 1.5rem;
    margin-bottom: 10px;
}

.body {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;

    //background-color: #5b5757;
    width: 100%;
    margin: 0 auto;

    .children {
        flex: 0 0 15.2%;
        margin-right: calc(10% / 6);
        margin-bottom: 20px;
        width: 120px;
        height: 120px;
        background-color: #F2F5F9;
        border-radius: 5px;
        position: relative;

        img {
            width: 40px;
            height: 40px;
            position: absolute;
            top: 40%;
            left: 50%;
            margin-top: -20px;
            margin-left: -20px;
        }

        > p {
            text-align: center;
            line-height: 170px;
        }
    }

    .children:nth-child(6n) {
        margin-right: 0;
    }
}
</style>
