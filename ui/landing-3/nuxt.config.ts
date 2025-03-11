// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  // plugins: ['@/plugins/axios'],
  modules: [
    '@nuxt/eslint',
    '@nuxt/image',
    '@nuxt/ui-pro',
    '@nuxt/content',
    '@ant-design-vue/nuxt'
    // '@nuxtjs/proxy'
  ],

  runtimeConfig: {
    public: {
      API_BASE_DEV: 'http://localhost:8080',
      API_BASE_PROD: 'http://localhost:8080'
    }
  },

  devtools: {
    enabled: true
  },

  css: ['~/assets/css/main.css'],

  content: {
    preview: {
      api: 'https://api.nuxt.studio'
    }
  },

  mdc: {
    highlight: {
      noApiRoute: false
    }
  },

  routeRules: {
    '/': {prerender: true}
  },

  future: {
    compatibilityVersion: 4
  },

  compatibilityDate: '2025-01-15',

  typescript: {
    strict: false
  },

  eslint: {
    config: {
      stylistic: {
        commaDangle: 'never',
        braceStyle: '1tbs'
      }
    }
  }
})
