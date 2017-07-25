//我的评价
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" class="header"/>
    <div class="valuation">
    <div class="haed-title">综合评分</div>
    <Rate disabled v-model="valueDisabled" class="haed-title"></Rate>
    <div class="haed-title">共有<strong>56</strong>条评论</div>
    <div class="valuation-content">
        <img src="../../assets/img/touxiang2.png" />
        <div >
            <span class="nickname">毛毛头</span>
            <span class="comment">下雨天，依然来车很准时，赞！</span>
            <Rate disabled v-model="valueDisabled"></Rate>
        </div>
    </div>
        <div class="valuation-content">
        <img src="../../assets/img/touxiang2.png" />
        <div >
            <span class="nickname">毛毛头</span>
            <span class="comment">下雨天，依然来车很准时，赞！</span>
            <Rate disabled v-model="valueDisabled"></Rate>
        </div>
    </div>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data(){
    return {
      headerText : "我的评价",
      valueDisabled: 2
    }
  }
  ,
  components: {
    OHeader
  },
  methods: {
    show_suggest(key) {
        alert("333");
      this.$store.dispatch('show_suggest', key)
      this.$router.push({ path: '/mapLocation' })
    }
  }
}
</script>
