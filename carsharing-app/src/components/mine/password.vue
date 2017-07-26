//修改密码页
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" />
    <div class="password">
      <mt-field placeholder="请输入密码" type="password" v-model="password">
        <span class="icon-Password03"></span>
      </mt-field>
      <mt-field placeholder="请再次输入密码" type="password" v-model="again_password">
        <span class="icon-pass02"></span>
      </mt-field>
    </div>
    <div class="password-btn">
      <mt-button @click="savePassword">保存</mt-button>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import allData from '@/api/services/employee.service'
export default {
  data(){
    return {
      headerText : "密码设置",
      password: '',
      again_password: ''
    }
  },
  components: {
    OHeader
  },
  methods: {
    savePassword: function() {
      if(this.password === this.again_password) {
         allData.changePassword({password: this.password}, (res)=> {
           console.log(res);
         })
      }else {
        alert('两次输入密码不一致，请重新输入');
      }
       
    }
  }
}
</script>
