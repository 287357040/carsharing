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
  
      <!-- <div class="password-btn">
        <mt-button @click.stop="savePassword">保存</mt-button>
      </div> -->
      <div class="submit-btn" @click.stop="savePassword">保存</div>
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
    },
    savePassword: function () {
      if (this.password === this.again_password) {
        apiHandler.changePassword({ password: this.password }, (res) => {
          console.log(res);
        })
      } else {
        alert('两次输入密码不一致，请重新输入');
      }
    }
  }
}
</script>
