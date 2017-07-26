<template>
  <div class="navigator-wrapper linear-gradient-bg">
    <div class="navigator">
      <div class="nav-wrapper" :class="{shadow:shadowActive}">
        <div class="avatar-wrapper" @click="telephone_input">
          <div class="avatar">
            <i class="icon-mine"></i>
          </div>
        </div>
        <div class="tab-wrapper">
          <select class="passenger-owner-model" v-model="identity">
            <option selected>乘客</option>
            <option>司机</option>
          </select>
        </div>
        <div class="message-wrapper">
          <div class="message">
            <i class="icon-Information01"></i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import bus from '@/scripts/eventBus'
export default {
  data: () => {
    return {
      identity: '乘客'
    }
  },
  created: function(){
    let isDriver = bus.$on('isDriver');
      if(isDriver) {
        this.identity = '司机';
      }
  },
  watch: {
      identity: function(val){
          this.$emit('identityName',val);
      }
  },
  computed: {
    ...mapGetters([
      'shadowActive',
    ])
  },
  methods: {
    ...mapActions([
      'telephone_input'
    ]),
    

  }
}
</script>
<style>

</style>