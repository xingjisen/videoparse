<template>
    <Formwork>
        <template #title>
            {{ $t('content.Twitter.title') }}
        </template>
        <template #sub-title>
            {{ $t('content.Twitter.subTitle') }}
        </template>
        <template #shows>
            <div class="shows">
                <h1>{{ $t('content.Twitter.topLeft') }}</h1>
                <p>https://x.com/WeAreMessi/status/1604556916721451008</p>
                <p>https://twitter.com/ChampionsLeague/status/1695075341646155881</p>
                <p>https://x.com/LMessiStuff/status/1604555259249979392</p>
                <p>https://twitter.com/ChampionsLeague/status/1696631036006793434</p>
                <p>https://x.com/UEFA/status/1492584093799833605</p>
                <p>https://twitter.com/UEFA/status/1673687625763377153</p>
            </div>
        </template>
    </Formwork>

</template>
<script setup>
import Formwork from '~/components/formwork/index.vue'
</script>
<style scoped>
.shows {
    h1 {
        font-weight: bold;
        font-size: 1.5rem;
    }

    h2 {
        margin-top: 20px;
        font-size: 1.2rem;
    }
}
</style>
