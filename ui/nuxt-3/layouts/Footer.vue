<template>
    <div>
        <a-layout-footer style="text-align: center">
            <div class="text-center">{{ $t('Footer.msg1') }}
                <router-link to="/b/terms">{{ $t('Footer.msg2') }}</router-link>
                {{ $t('Footer.msg3') }}
            </div>
            <div class="footer_nav">
                <div>
                    <router-link to="/b/faq">{{ $t('Footer.faq') }}</router-link>
                </div>
                <div>
                    <router-link to="/b/terms">{{ $t('Footer.terms') }}</router-link>
                </div>
                <div>
                    <router-link to="/b/privacy">{{ $t('Footer.privacy') }}</router-link>
                </div>
                <div>
                    <router-link to="/b/copyright">{{ $t('Footer.copyright') }}</router-link>
                </div>
                <div>
                    <router-link to="/b/contact">{{ $t('Footer.contact') }}</router-link>
                </div>
            </div>
            Copyright © 2025 <span>DownloadTheVideo.online</span> {{ $t('Footer.ft') }}
        </a-layout-footer>
    </div>
</template>
<script setup>
</script>
<style scoped lang="scss">
.footer_nav {
    margin: 0 auto 20px;
    width: 800px;
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;

    a {
        color: #656565;
    }

    a:hover {
        color: #000;
    }
}

.text-center {
    margin-bottom: 20px;
}

.layout .ant-layout-footer {
    border-top: 1px solid #E4E4E7;

    span {
        font-size: 1.1rem;
        color: #9CA3AF;
        font-weight: bold;
    }
}
</style>
