//行程设置
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" />
    <div class="password">
      <a @click="show_suggest('getOn')">
      <mt-field  placeholder="公司" >
        <span class="icon-corporation icon" >
                
        </span>
        <!-- <i class="fa fa-lock icon"></i> -->
      </mt-field>
      </a>

      <a  @click="show_suggest('getOff')">
      <mt-field placeholder="家">
        <!-- <i class="fa fa-get-pocket icon"></i> -->
        <span class="icon-home icon"  >
                
        </span>
      </mt-field>
      </a>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data(){
    return {
      headerText : "行程设置"
    }
  }
  ,
  components: {
    OHeader
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      this.$router.push({ path: '/mapLocation' })
    }
  }
}
</script>
