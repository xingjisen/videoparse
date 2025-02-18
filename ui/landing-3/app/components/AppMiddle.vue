<template>
  <div>
    <div class="title">支持的平台</div>
    <div class="body">
      <div class="children" v-for="videoIcon in videoIcons">
        <img :src="`/img/videoIcon/${videoIcon.href}`" alt="">
        <p>{{ videoIcon.title }}</p>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import {onMounted} from "@vue/runtime-core";
const videoIcons = ref([
  {title: 'Facebook',
    href: 'facebook.svg'},
  {title: 'Twitter',
    href: 'twitter.svg'},
  {title: 'Instagram',
    href: 'instagram.svg'},
  {title: 'Telegram',
    href: 'telegram.svg'},
  {title: 'TikTok',
    href: 'tiktok.svg'},
  {title: '9GAG',
    href: '9gag.svg'},
  {title:'Mashable',href:'mashable.svg'},
  {title:'Linkedin',href:'linkedin.svg'},
  {title:'Likee',href:'likee.svg'},
  {title:'Kwai',href:'kwai.svg'},
  {title:'Imgur',href:'imgur.svg'},
  {title:'Imdb',href:'imdb.svg'},
  {title:'Ifunny',href:'ifunny.svg'},
  {title:'Gaana',href:'gaana.svg'},
  {title:'Flickr',href:'flickr.svg'},
  {title:'Febspot',href:'febspot.svg'},
  {title:'Espn',href:'espn.svg'},
  {title:'Akillitv',href:'akillitv.svg'},
  {title:'Bandcamp',href:'bandcamp.svg'},
  {title:'Bitchute',href:'bitchute.svg'},
  {title:'Blogger',href:'blogger.svg'},
  {title:'Blutv',href:'blutv.svg'},
  {title:'Buzzfeed',href:'buzzfeed.svg'},
  {title:'Dailymotion',href:'dailymotion.svg'},
])

onMounted(()=>{
})

</script>
<style lang="scss">
.title{
  font-weight: bold;
  font-size: 1.5rem;
  margin-bottom: 10px;
}
.body{
  display: flex;
  justify-content: flex-start;
  flex-wrap: wrap;

  //background-color: #5b5757;
  width: 900px;
  max-width: 1000px;
  margin: 0 auto;
  .children{
    flex: 0 0 15.2%;
    margin-right: calc(10% / 6);
    margin-bottom: 20px;
    width: 120px;
    height: 120px;
    background-color: #F2F5F9;
    border-radius: 5px;
    position: relative;
    img{
      width: 40px;
      height: 40px;
      position: absolute;
      top: 40%;
      left: 50%;
      margin-top: -20px;
      margin-left: -20px;
    }
    >p{
      text-align: center;
      line-height: 170px;
    }
  }
  .children:nth-child(6n) {
    margin-right: 0;
  }
}
</style>
