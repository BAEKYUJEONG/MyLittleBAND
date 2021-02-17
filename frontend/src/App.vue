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

#app {
    position:relative;
    z-index:1;
}

#app:after {
    background-image: url("~@/assets/image/background.jpg");
    background-attachment: fixed;
    top:0;
    left:0;
    position:absolute;
    background-size: cover;
    opacity:0.5!important;
    filter:alpha(opacity=50);
    z-index:-1;
    content:"";
    width:100%;
    height:100%;
}
</style>