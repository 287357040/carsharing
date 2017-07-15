<template>
    <div class="mask-container" >
        <common-mask v-show="$store.state.ismask"></common-mask>
        <div class="login-popup showPopup animated zoomIn " style="margin-top: 0px; display: block; top: 241px;">
            <check-telephone v-show="checkTelephone" ></check-telephone>
            <message-confirm v-if="messageConfirm" ></message-confirm>
        </div>
    </div>
</template>
<script>
import {mapActions,mapState} from 'vuex'
import checkTelephone from '@/components/checkTelephone.vue'
import messageConfirm from '@/components/messageConfirm.vue'
import commonMask from '@/components/commonMask.vue'
export default {
  computed: {
    ...mapState([
        'maskType',
        'messageConfirm',
        'checkTelephone'
        ])
  },
    methods: {
        ...mapActions(['switch_mask'])
    },
    components: {
        checkTelephone,
        messageConfirm,
        commonMask
        
    }
}
</script>
<style scoped>
.login-popup{
    animation-duration:.5s;
}


.login-popup {
    position: fixed;
    left: 50%;
    margin-left: -133px;
    z-index: 666;
    width: 267px;
    box-sizing: border-box;
    border-radius: 2px;
    background: #fff;
    font-family: PingFang SC, STHeitiSC-Light, Helvetica-Light, arial, sans-serif;
    font-size: 14px;
    -webkit-user-select: none;
    user-select: none;
}
</style>