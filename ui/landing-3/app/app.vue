<script setup lang="ts">

import { fr } from '@nuxt/ui/locale'

</script>
<template>
  <UApp :toaster="{ expand: false }" :locale="fr">
    <AppHeader />
    <div style="width: 900px;margin: 0 auto">
<!--      <UMain>-->
        <NuxtPage />
<!--      </UMain>-->
      <AppMiddle />
      <AppFooter />
    </div>
  </UApp>
</template>

<style>
.input-btn .truncate{
  text-align: center;
  display: inline-block;
  width: 100%;
}
</style>
