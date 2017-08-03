<template>
    <div class="mine-container mine-container-bgcolor">
        <OHeader v-bind:headerText="headerText" />
        <div class="submit-btn" @click.stop="submintInfo">提交</div>
        <div class="license-wrapper">
            <p class="hint-text">请填写以下信息，我们将优先审核，这个软件仅供内部使用，请放心填写</p>
            <ul class="license-info">
                <li>
                    <mt-field label="真实姓名" placeholder="真实姓名与驾照不符将无法审核" type="username" v-model="driverName">
                    </mt-field>
                    <mt-field label="驾驶证号" placeholder="请输入驾驶证号" type="username" v-model="driverNumber">
                    </mt-field>
    
                    <mt-field label="机动车行驶编号" placeholder="请输入" type="username" v-model="carNumber">
                    </mt-field>
                </li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
export default {
    data() {
        return {
            headerText: "驾驶证认证",
            driverName: '',
            driverNumber: '',
            carNumber: ''
        }
    },
    components: {
        OHeader
    },
    methods: {
        submintInfo() {

        }
    }
}
</script>
<style scoped>
.mint-cell-title {
    width: auto !important;
    text-align: left !important;
    float: left;
}
</style>