<template>
  <v-app>
    <!-- Header, Content, Footer로 구성 -->
    <Header />
    <router-view />
    <Footer />
  </v-app>
</template>

<script>
import Header from './components/header/Header';
import Footer from './components/footer/Footer';

export default {
  name: 'App',

  components: {
    Header,
    Footer,
  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap');
body {
  font-family: 'Noto Sans', sans-serif;
}
</style>