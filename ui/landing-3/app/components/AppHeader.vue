<script setup lang="ts">
const nuxtApp = useNuxtApp()
const { activeHeadings, updateHeadings } = useScrollspy()

const items = computed(() => [{
  label: 'TikTok',
  to: '/tiktok',
  // active: activeHeadings.value.includes('features') && !activeHeadings.value.includes('templates')
}, {
  label: 'Instagram',
  to: 'instagram',
  active: activeHeadings.value.includes('templates') && !activeHeadings.value.includes('pricing')
}, {
  label: 'Twitter',
  to: 'twitter',
  active: activeHeadings.value.includes('pricing') && !activeHeadings.value.includes('testimonials')
}, {
  label: 'DouYin',
  to: 'douyin',
  active: activeHeadings.value.includes('testimonials') && !activeHeadings.value.includes('faq')
}, {
  label: 'KuaiShou',
  to: 'kuaishou',
  active: activeHeadings.value.includes('faq')
}])

nuxtApp.hooks.hookOnce('page:finish', () => {
  updateHeadings([
    document.querySelector('#features'),
    document.querySelector('#templates'),
    document.querySelector('#pricing'),
    document.querySelector('#testimonials'),
    document.querySelector('#faq')
  ])
})
</script>

<template>
  <UHeader>
    <template #left>
      <NuxtLink to="/">
        <LogoPro class="w-auto h-6 shrink-0" />
      </NuxtLink>
    </template>

    <UNavigationMenu
      :items="items"
      variant="link"
    />

    <template #right>
      <UColorModeButton />

      <TemplateMenu />
<!--      <UButton
        to="https://github.com/nuxt-ui-pro/landing/tree/v3"
        target="_blank"
        icon="i-simple-icons-github"
        aria-label="GitHub"
        color="neutral"
        variant="ghost"
      />-->
    </template>

    <template #content>
      <UNavigationMenu
        :items="items"
        orientation="vertical"
        class="-mx-2.5"
      />
    </template>
  </UHeader>
</template>
