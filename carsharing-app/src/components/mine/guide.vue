//拼车指南页
<template>
    <div>
        <OHeader v-bind:headerText="headerText" />
        <div class="mine-container">
            <div class="guid">
                <h3>一、拼车指南</h3>
                <p>1、乘客与司机之间的费用可以自行商量，本公司只提供拼车软件、不参与费用
                    <br />
                    <br /> 2、乘客可以发感谢费，或者用早餐晚餐来做补偿
                    <br />
                    <br /> 3、如果有过路费，也可以AA制，这块主要看车主心情
                    <br />
                    <br />
                </p>
            </div>
            <div class="guid">
                <h3>一、拼车指南</h3>
                <p>1、乘客与司机之间的费用可以自行商量，本公司只提供拼车软件、不参与费用
                    <br />
                    <br /> 2、乘客可以发感谢费，或者用早餐晚餐来做补偿
                    <br />
                    <br /> 3、如果有过路费，也可以AA制，这块主要看车主心情
                    <br />
                    <br />
                </p>
            </div>
        </div>
    </div>
</template>

<script>
import OHeader from '@/components/mine/header.vue'
export default {
  data(){
    return {
      headerText : "拼车指南"
    }
  }
  ,
  components: {
    OHeader
  }
}
</script>

