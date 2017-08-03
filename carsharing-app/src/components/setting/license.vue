<template>
    <div class="mine-container mine-container-bgcolor">
        <OHeader v-bind:headerText="headerText" />
        <div class="submit-btn" @click.stop="saveDriverInfo">提交</div>
        <div class="license-wrapper">
            <p class="hint-text">请填写以下信息，我们将优先审核，这个软件仅供内部使用，请放心填写</p>
            <ul class="license-info">
                <li class="license-li">
                    <mt-field label="驾驶证号" placeholder="请输入驾驶证号" type="txt" v-model="driverNumber"></mt-field>
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
                <li class="license-li">
                    <mt-field label="机动车行驶编号" placeholder="请输入" type="txt" v-model="carNumber">
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
import { MessageBox } from 'mint-ui';
import Store from '@/utils/store'
import userService from '@/api/services/employee.service'
import driverService from '@/api/services/driver.service'
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
        },
        saveDriverInfo:function(){
            var driverInfo =  Store.fetch("newDriverInfo");
            driverInfo.userLicense = this.driverNumber;
            driverInfo.licenseDate = this.date1;
            driverInfo.validDate = this.date2;
            driverInfo.carLicense = this.carNumber;
            Store.save("newDriverInfo",driverInfo);
            var userInfo = Store.fetch("user");

            if(userInfo.isDriver){

            userInfo.isDriver = 1;

            userService.updateUserInfo(userInfo, (msg) => {
                Store.save("user", userInfo);
            }, (err) => {
                MessageBox(err);
            });

            driverService.regToDriver(driverInfo, (msg) => {
                Store.save("newUserInfo", driverInfo);
            }, (err) => {
                Store.save("newUserInfo", null);
                MessageBox(err);
               this.$router.path("/home");
            });
            }
            else
            {
                driverService.regToDriver(driverInfo, (msg) => {
                Store.save("newUserInfo", driverInfo);
                this.$router.path("/home");
                }, (err) => {
                MessageBox(err);
                });
            }
        }
    },
    created:function(){
         var driverInfo =  Store.fetch("newDriverInfo");
            this.driverNumber = driverInfo.userLicense;
            this.date1 = driverInfo.licenseDate;
            this.date2 = driverInfo.validDate;
            this.carNumber = driverInfo.carLicense;
    }
}
</script>