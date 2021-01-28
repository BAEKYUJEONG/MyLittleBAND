<template>
  <div> 
  <v-app>
    <!-- Header, Content, Footer로 구성 -->
    <Header />
    <router-view />
    <Footer />
  </v-app>
  <router-view/>
  </div>
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
</style>