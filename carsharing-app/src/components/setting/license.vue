<template>
    <div class="mine-container mine-container-bgcolor">
        <OHeader v-bind:headerText="headerText" />
        <div class="submit-btn" @click.stop="submintInfo">提交</div>
        <div class="license-wrapper">
            <p class="hint-text">请填写以下信息，我们将优先审核，这个软件仅供内部使用，请放心填写</p>
            <ul class="license-info">
                <li>
                    <mt-field class="border-bottom" label="真实姓名" placeholder="真实姓名与驾照不符将无法审核" type="txt" v-model="driverName"></mt-field>
                </li>
                <li>
                    <mt-field class="border-bottom" label="驾驶证号" placeholder="请输入驾驶证号" type="txt" v-model="driverNumber"></mt-field>
                </li>
                <li class="license-li pad" @click="openDatePicker1">
                    <span class="text">初次领证日期</span>
                    <span class="date">{{date1}}</span>
                    <span class="icon-Level-Down ico right"></span>
                </li>
                <li class="license-li pad" @click="openDatePicker2">
                    <span class="text">有效期</span>
                    <span class="date">{{date2}}</span>
                    <span class="icon-Level-Down ico right"></span>
                </li>
                <li>
                    <mt-field class="border-bottom" label="机动车行驶编号" placeholder="请输入" type="username" v-model="carNumber">
                    </mt-field>
                </li>
            </ul>
        </div>
        <mt-datetime-picker ref="picker1" type="date" year-format="{value} 年" month-format="{value} 月" date-format="{value} 日" @confirm="handleConfirm1">
        </mt-datetime-picker>
        <mt-datetime-picker ref="picker2" type="date" year-format="{value} 年" month-format="{value} 月" date-format="{value} 日" @confirm="handleConfirm2">
        </mt-datetime-picker>
    </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import { Toast } from 'mint-ui';
export default {
    data() {
        return {
            headerText: "驾驶证认证",
            driverName: '',
            driverNumber: '',
            carNumber: '',
            date1: '',
            date2: ''
        }
    },
    components: {
        OHeader
    },
    methods: {
        submintInfo() {
            Toast({
                message: '提交成功',
                iconClass: 'icon icon-success'
            });
        },
        openDatePicker1() {
            this.$refs.picker1.open();
        },
        openDatePicker2() {
            this.$refs.picker2.open();
        },
        handleConfirm1(val) {
            this.date1 = this.formatDate(val);
        },
        handleConfirm2(val) {
            this.date2 = this.formatDate(val);
        },
        formatTen(num) {
            return num > 9 ? (num + "") : ("0" + num);
        },
        formatDate(date) {
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            return year + "-" + this.formatTen(month) + "-" + this.formatTen(day);
        }
    }
}
</script>