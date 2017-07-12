<template>
  <div id="app">
    <v-header></v-header>
    <v-login></v-login>
    <router-view></router-view>
  </div>
</template>

<script>
import vHeader from './header.vue'
import vLogin from '../login/login.vue'
import { mapActions, mapState } from 'vuex'

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

  },
  methods: {
    // firstLocation() {
    //   if (!this.hasLocation) {

    //   }
    // },
    add() {
      // Emit the server side 
      this.$socket.emit("add", { a: 5, b: 3 });
    },

    get() {
      this.$socket.emit("get", { id: 12 }, (response) => {
      });
    },
    ...mapActions([
      'get_telephone'
    ])
  },
  components: {
    vHeader,
    vLogin
  },
  mounted() {
    this.get_telephone();
    // this.$store.dispatch('getCurrentPosition', 'AMap')
  },
  socket: {
    // Prefix for event names 
    // prefix: "/counter/", 

    // If you set `namespace`, it will create a new socket connection to the namespace instead of `/` 
    // namespace: "/counter", 

    events: {

      // Similar as this.$socket.on("changed", (msg) => { ... }); 
      // If you set `prefix` to `/counter/`, the event name will be `/counter/changed` 
      // 
      changed(msg) {
        console.log("Something changed: " + msg);
      }

    }
  }
}
</script>
