//乘客未评价
<template>
    <div class="setting-container">
        <OHeader v-bind:headerText="headerText" />
        <!--地址定位  -->
        <el-amap :zoom="zoom" :center="center" class="comment-map" style="height:314px;" :style="{display:isShowMap}">
            <el-amap-marker :key="marker.lng" v-for="marker in markers" :position="marker"></el-amap-marker>
        </el-amap>
        <div class="comment-datail" @click="show_position(item)" v-bind:key="item" v-for="item in passagesList">
            <div class="comment-datail-txt">
                <span class="icon-date ico">
    
                </span>
                <span class="txt-content txt-content-color">7月12日上午7:00</span>
            </div>
            <div class="comment-datail-txt">
                <span style="margin-left:30px" class="txt-content txt-content-color">下沙龙湖天街-江南大道3588号恒生大厦</span>
            </div>
            <div>
                <div class="horline"></div>
                <div>
                    <img width="97px" height="81px" src="../../assets/img/photo.png" />
                    <div class="comment-person-datail-content ">
                        <span class="comment-person-datail ">阿康哥</span>
                        <span class="comment-person-datail ">浙ALZ114</span>
                        <span class="comment-person-datail ">白色 吉利帝豪</span>
                    </div>
                    <span class="icon-telephone right" @click="show_model"></span>
                    <span class="icon-telephone right"></span>
                    <span class="icon-telephone right"></span>
                </div>
                <div class="passages-comment-list-bg" :style="{display:item.isShowDetail}">
                    <div class="has-passanger-comment">
                        <div class="passanger-comment-spiltline"></div>
                        <div>Ta对我的评价</div>
                        <div class="passanger-comment-spiltline"></div>
                        <Star v-bind:score="compositeScore" />
                        <div class="has-passanger-comment-input">
                            <span class="triangle"></span>
                            <span class="has-passanger-comment-input">你对司机说，司机大哥太贴心了下雨还把…</span>
                        </div>
                    </div>
                    <div class="has-passanger-comment">
                        <div class="passanger-comment-spiltline"></div>
                        <div>我对ta的评价</div>
                        <div class="passanger-comment-spiltline"></div>
                        <Star v-bind:score="compositeScore" />
                        <div class="has-passanger-comment-input" v-if="isFinished == true">
                            <span class="triangle"></span>
                            <span class="has-passanger-comment-input">你对司机说，司机大哥太贴心了下雨还把…</span>
                        </div>
                        <div class="comment-input" v-if="isFinished == false">
                            <input placeholder="有什么相对ta讲的吗" />
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <div class="submit-btn" @click.stop="finishRoute">结束行程</div>

    </div>
</template>

<script>
import OHeader from '@/components/mine/header.vue'
import Star from '@/components/public/starScore.vue'
import apiHandler from '@/api/services/route.service'
import demandService from '@/api/services/demand.service'
import { MessageBox } from 'mint-ui';
export default {
    data() {
        return {
            center: [],
            zoom: 15,
            headerText: "我的行程",
            markers: [],
            routeId:'',
            popupPlate: false,
            isFinished: false,
            isShowMap: 'block',
            compositeScore:3,
            passagesList: [
                {
                   isShowDetail:'none' 
                }
            ]
        }
    },
    created: function () {
        this.center = [120.1552070000, 30.2736900000];
        this.markers.push([120.1552070000, 30.2736900000])
        this.isShowMap = this.isFinished ? 'none' : 'block';
        this.routeId =  this.$route.query.routeId;
        demandService.getRideDemandsByRouteId(this.routeId, data => {
            this.passagesList = data;
            
            for (var i = 0; i < this.passagesList.length; i++)
            this.passagesList[i].isShowDetail = 'none';
        }, err => {
            MessageBox("获取服务失败！");
        });

    },
    components: {
        OHeader,
        Star
    },
    methods: {
        finishRoute: function () {
            apiHandler.finishRoute(this.routeId,success=>{
                 MessageBox("已经结束行程!");
                 this.$router.push({path:'home'});
            },err=>{
                 MessageBox("取消失败!");
            });
        },
        show_position:function(item){
         //console.log(item.endLongitude+"--"+item.endLatitude);
           this.markers = [];
           this.center = [item.endLongitude, item.endLatitude];
           this.markers.push([item.endLongitude, item.endLatitude]);
        },
        show_model:function(item){
            for (var i = 0; i < this.passagesList.length; i++)
                this.passagesList[i].isShowDetail = 'none';
            item.isShowDetail = 'block'
        }
    }
}
</script>

<style>

</style>
