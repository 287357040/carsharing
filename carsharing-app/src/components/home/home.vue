<template>
  <div id="app">
    <v-header></v-header>
    <router-view></router-view>
  </div>
</template>

<script>
  import vHeader from './header.vue'
  import {mapActions, mapState} from 'vuex'

  // import vFooter from '@/components/footer.vue'
  // import mapState from 'vuex'
  export default {
    computed: {
      ...mapState([
        'usersidebar',
        'checkTelephone',
        'ismask',
        'hasLocation'
      ])
    },
    created() {
      // if(this.$router.path)
      // components.log(this.$router.path)
      // this.$router.replace({path:'home/taxi'})
    },
    methods: {
      // firstLocation() {
      //   if (!this.hasLocation) {

      //   }
      // },
      ...mapActions([
        'get_telephone'
      ])
    },
    components: {
      vHeader
    },
    mounted() {
      this.get_telephone();
      this.$store.dispatch('getCurrentPosition', 'AMap')
    }
  }
</script>
