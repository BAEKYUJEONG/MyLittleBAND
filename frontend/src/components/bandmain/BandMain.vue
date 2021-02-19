<template>
  <v-main>
    <v-container>
      <!-- BandList -->
      <BandList />
      <v-divider></v-divider>
      <!-- FollowingBandList -->
      <FollowingBandList />
      <v-divider></v-divider>
      <SuggestBandList />
      <!-- SuggestBandList -->
    </v-container>
  </v-main>
</template>

<script>
import BandList from "./BandList";
import FollowingBandList from "./FollowingBandList";
import SuggestBandList from "./SuggestBandList"
export default {
  components: {
    BandList,
    FollowingBandList,
    SuggestBandList
  },
};
</script>

<style>
</style>