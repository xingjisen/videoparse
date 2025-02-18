<template>
  <UDropdownMenu
    v-model="language"
    v-slot="{ open }"
    :modal="false"
    :items="items"
    @click="console.log(`代码行🚀: 1123 ~ -> `,1123)"
    :ui="{ content: 'w-(--reka-dropdown-menu-trigger-width) min-w-0' }"
    size="xs"
  >
    <UButton
      :label="language"
      variant="subtle"
      trailing-icon="i-tabler:language-katakana"
      size="xs"
      class="-mb-[6px] font-semibold rounded-full truncate"
      :class="[open && 'bg-(--ui-primary)/15']"
      :ui="{
        trailingIcon: ['transition-transform duration-200', open ? 'rotate-180' : undefined].filter(Boolean).join(' ')
      }"
    />
  </UDropdownMenu>
</template>
<script setup>
const language = ref('English')
const items = ref([{
  label: `English`,
  // to: 'https://ui-pro-starter.nuxt.dev/',
  color: 'primary',
  checked: true,
  type: 'radio',
  onSelect() {
    console.log('English')
    language.value = 'English'
  }
}, {
  label: `中文`,
  // to: 'https://landing-template.nuxt.dev/',
  color: 'primary',
  checked: true,
  type: 'radio',
  onSelect() {
    console.log('中文')
    language.value = '中文'
  }
}, {
  label: `しろうと`,
  // to: 'https://docs-template.nuxt.dev/',
  color: 'primary',
  checked: true,
  type: 'radio',
  disabled: true,
}, {
  label: `한국어`,
  // to: 'https://saas-template.nuxt.dev/',
  color: 'primary',
  checked: true,
  type: 'radio',
  disabled: true,
}])
</script>
