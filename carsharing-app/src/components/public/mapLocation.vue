<template>
<transition enter-active-class="animated fadeInUpBig">
    <div class="suggest">
      <suggest-head></suggest-head>
    </div>
</transition>
</template>
<script>
import {mapState} from 'vuex'
import suggestHead from '@/components/public/suggestHead.vue'

export default {
  computed: {
    ...mapState([
     
    ])
  },
  components: {
    suggestHead
  }
}
</script>
<style>
.suggest{
  position: relative;
  z-index: 999;
  height: 100%;
  animation-duration: .5s;
}
.amap{
  height: 94%;
  width: 100%;
  position: absolute;
  z-index: 800;
  /*background-color: green;*/
}
.mint-popup.mint-popup-right{
  width: 100%;
  height: 100%;
  background-color: #f3f4f5;
}
</style>
