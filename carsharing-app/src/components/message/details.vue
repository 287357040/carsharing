//????
<template>
  <div class="mine-container">
    <OHeader v-bind:headerText="headerText" />
    <div class="details">
        <div class="title">
          {{this.$route.query.title}}
        </div>
        <div class="title2">
           {{this.$route.query.time}}
        </div>
        <div class="details-content">
           {{this.$route.query.content}}
        </div>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import allData from '@/api/services/employee.service'
export default {
  data(){
    return {
      headerText : "????",
      password: '',
      again_password: ''
    }
  },
  components: {
    OHeader
  },
  methods: {
  },
  created: function () {
  }
}
</script>
