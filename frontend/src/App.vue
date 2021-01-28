<template>
  <div> 
  <v-app>
    <Header />
    <!-- 테스트 용으로 컴포넌트 붙임 -->
    
  </v-app>
  <router-view/>
  </div>
</template>

<script>
import Header from './components/header/Header';
export default {
  name: 'App',

  components: {
    Header,
    
  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
</style>