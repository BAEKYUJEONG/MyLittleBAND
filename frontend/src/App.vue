<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <Header />
    <Main />
    <Footer/>
  </div>
</template>

<script>
import Footer from './components/footer/Footer.vue'
import Header from './components/header/Header.vue'
import Main from './components/main/Main.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer,
    Main
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
