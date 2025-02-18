<script setup lang="ts">
const columns = [{
  label: '资源',
  children: [{
    label: '帮助中心'
  }, {
    label: '文档'
  }, {
    label: '路线图'
  }, {
    label: '更改日志'
  }]
}, {
  label: '特征',
  children: [{
    label: '子公司'
  }, {
    label: '门户'
  }, {
    label: '工作'
  }, {
    label: '赞助商'
  }]
}, {
  label: '公司',
  children: [{
    label: '大约'
  }, {
    label: '定价'
  }, {
    label: '职业'
  }, {
    label: '博客'
  }]
}]

const toast = useToast()

const helpDoc = ref([
  {
    label: 'TiQu.cc是什么？',
    content: 'TiQu.cc是一个强大的在线工具，让用户可以从包括Facebook、VK、Twitter、Tiktok、Instagram等在内的100多个平台下载他们喜爱的视频。不论是音乐、电视节目、电影、短片还是个人上传的内容，TiQu.cc都可以帮助您随时随地以离线方式获取自己所喜爱的视频内容。'
  },
  {
    label: 'TiQu.cc支持哪些平台？',
    content: 'TiQu.cc支持100多个平台，涵盖包括Facebook、Twitter、Instagram、Tiktok、LinkedIn在内的各种社交网络，VK、Vimeo、Dailymotion等视频平台，Twitch等直播服务，Soundcloud和Bandcamp等音频服务，Imgur和Flickr等图片网络，Blogger和Tumblr等博客网站，Reddit等讨论板块，以及更多。因此，它涵盖了综合的方式，并考虑了用户多样的偏好。'
  },
  {
    label: '使用TiQu.cc是免费的吗？',
    content: '是的，TiQu.cc完全免费。我们理解获得数字内容的便利性的重要性，因此我们不希望将其束之高阁。您可以无需支付任何费用下载任意数量的视频。我们致力于提供一个对所有人都可访问的服务，不受社会经济约束和地域限制，使虚拟世界可在全球范围内访问。'
  },
  {
    label: '使用TiQu.cc下载视频是否合法？',
    content: '通常取决于您要下载的视频及其版权情况。虽然TiQu.cc为您提供了从各种平台检索视频的服务，但用户有责任尊重并遵守各个视频平台或内容创作者的版权政策。我们强烈建议仅将下载用于个人使用或为任何其他用途获得相应版权持有者的许可。'
  },
  {
    label: '视频下载不开始时该怎么办？',
    content: '首先，检查您的互联网连接，确保稳定。如果互联网连接没有问题，尝试刷新页面或重新启动Web浏览器。如果仍然遇到问题，请确保您使用的是最新版本的浏览器，或尝试更换其他浏览器。这是因为有时候某些浏览器可能存在限制或错误，导致下载出现问题。'
  },
  {
    label: '可以期待什么样的视频质量？',
    content: '下载的视频质量在很大程度上取决于原始上传视频的质量。如果原始视频支持高清或4K，您可以以相同质量下载视频。尽管TiQu.cc努力保持下载时的质量，但您必须明白转码视频（尤其是到不同格式）可能会对视频质量产生影响。'
  }
])

</script>

<template>
  <USeparator
    icon="i-simple-icons-nuxtdotjs"
    class="h-px"
  />

  <UFooter :ui="{ top: 'border-b border-(--ui-border)' }">
    <template #top>
      <UAccordion style="margin-bottom: 20px" type="multiple" :items="helpDoc" />
      <UContainer>
        <UFooterColumns :columns="columns">
          <template #right>
          </template>
        </UFooterColumns>
      </UContainer>
    </template>
    <template #left>
      <p class="text-sm text-(--ui-text-muted)">
        Copyright © {{ new Date().getFullYear() }}
      </p>
    </template>

    <template #right>

<!--      <UButton
        to="https://github.com/nuxt-ui-pro/landing/tree/v3"
        target="_blank"
        icon="i-simple-icons-github"
        aria-label="GitHub"
        color="neutral"
        variant="ghost"
      />-->
    </template>
  </UFooter>


</template>
