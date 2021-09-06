<template>
  <div id="app">
    <Header></Header>
    <Main></Main>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from '@/components/header/Header.vue'
import Main from '@/components/main/Main.vue'
import Footer from '@/components/footer/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Main,
    Footer
  }
}
</script>

<style>
  * {
    margin: 0;
    padding: 0;
  }
</style>
