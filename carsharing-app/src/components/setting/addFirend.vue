<template>
    <div class="owner-wrapper" style="background: #fff">
        <v-header v-bind:headerText="headerText" v-bind:isClose="isClose"></v-header>
        <div class="firend-wrapper">
            <div class="photo-location">
                <img width="97px" height="81px" src="../../assets/img/photo.png" />
            </div>
            <div>
                <span class="firend-nickName">{{userName}}</span>
                <span class="icon-girl"></span>
            </div>
    
            <section id="info-detail" v-if="isDriver">
                <p class="word-info">
                    <span>工号: </span>
                    <span>{{workNum}}</span>
                </p>
                <p class="word-info">
                    <span>车牌号: </span>
                    <span>{{plateNum}}</span>
                </p>
                <p class="word-info">
                    <span>车品牌: </span>
                    <span>{{carBrand}}</span>
                </p>
                <p class="word-info">
                    <span>联系电话: </span>
                    <span>{{contactPhone}}</span>
                </p>
                <span class="icon-telephone font-size"  @click="show_tel"></span>
            </section>
            <section id="info-detail" v-else>
                <p class="word-info">
                    <span>工号: </span>
                    <span>{{workNum}}</span>
                </p>
                <p class="word-info">
                    <span>联系电话: </span>
                    <span>{{contactPhone}}</span>
                </p>
                <span class="icon-telephone font-size" @click="show_tel"></span>
            </section>
            <section v-if="isFriend == false">
            <mt-button type="default" class="add-btn linear-gradient-bg" @click.stop="addFirend">加为好友</mt-button>
            </section>
            <section v-else>
                <span class="icon-Selected"></span> <span style="color:gray">已添加</span>
            </section>
        </div>
    </div>
</template>

<script>
import vHeader from '@/components/mine/header.vue'
import apiHandler from '@/api/services/employee.service'
import Store from '@/utils/store'
import { MessageBox } from 'mint-ui';
export default {
    data() {
        return {
            headerText: '详细资料',
            workNum: '12345',
            plateNum: '浙ALZ114',
            carBrand: '红色 别克昂克拉',
            contactPhone: '18726252626',
            userName: "一切皆有可能",
            isDriver: false,
            isClose :true,
            isFriend : false
        }
    },
    components: {
        vHeader
    },
    methods: {
        addFirend() {
            let userNo = Store.fetch('user').userNo;
            apiHandler.addFriend({
                userNo: this.userNo,
                friendNo: this.workNum
            }, data => {
                MessageBox("添加成功");
            }, err => {
                MessageBox("添加失败");
            });
        },
        show_tel:function(){
            MessageBox("请拨打电话:"+this.contactPhone);
        }
    },
    created: function () {
        this.workNum = this.$route.query.userNo;
        apiHandler.getUserByNo({
            userNo:this.workNum
        }, data => {
            this.isDriver = data.user.isDriver;
            this.userName = data.user.userName;
            this.plateNum = data.driver.region + data.driver.carNo;
            this.carBrand = data.driver.color + data.driver.model + data.driver.brand;
            this.contactPhone = data.user.mobile;
        }, err => { 

        });
        apiHandler.getFriends(data=>{
            console.log(data);
            for(let i=0;data!=undefined && i<data.length;i++)
            {
                if(data[i].userNo == this.workNum)
                {
                    isFriend = true;
                    return;
                }
            }
        },err=>{

        });
    }
}
</script>
<style scoped>
.firend-wrapper {
    position: relative;
    padding: 10px;
    margin-top: 60px;
    background: #fff;
}

.photo-location {
    margin: 20px auto 10px;
}

.firend-nickName {
    color: #333;
    margin-right: 10px;
}

#info-detail {
    padding: 20px;
}

#info-detail .word-info {

    text-align: left;
    height: 40px;
    font-size: 16px;
    color: #747579;
    letter-spacing: 0;
    line-height: 40px;
}

.add-btn {
    width: 80%;
    height: 50px;
    border-radius: 100px;
    color: #fff;
}

.font-size {
    font-size: 40px;
    float: right;
    margin-top: -50px;
}
</style>


