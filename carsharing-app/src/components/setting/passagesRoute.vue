//乘客未评价
<template>
    <div class="setting-container">
        <OHeader v-bind:headerText="headerText" v-bind:isClose="isClose" />
        <!--地址定位  -->
        <el-amap :zoom="zoom" :center="center" class="comment-map" style="height:314px;" :style="{display:isShowMap}">
            <el-amap-marker :key="marker.lng" v-for="marker in markers" :position="marker"></el-amap-marker>
        </el-amap>
        <div class="comment-datail">
            <div class="comment-datail-txt">
                <span class="icon-date ico">
    
                </span>
                <span class="txt-content txt-content-color">{{passagesList.startTime}}</span>
            </div>
            <div class="comment-datail-txt" @click="show_position(passagesList.endLongitude,passagesList.endLatitude)">
                <span style="margin-left:30px" class="txt-content txt-content-color">{{placeSlice(passagesList.startPlace)}}-{{passagesList.endPlace}}</span>
            </div>
            <div>
                <div class="horline"></div>
                <div>
                    <img width="97px" height="81px" src="../../assets/img/photo.png" />
                    <div class="comment-person-datail-content ">
                        <span class="comment-person-datail ">{{driverInfo.userName}}</span>
                        <span class="comment-carInfo" >{{driverInfo.region}}{{driverInfo.carLicense}}</span>
                        <span class="comment-person-datail ">{{driverInfo.color}}&nbsp;&nbsp;{{driverInfo.brand}}</span>
                        <span class="comment-person-datail ">
                            <strong>司机起:</strong>{{startPlaceShow}}</span>      <!-- 司机起点 -->
                        <span class="comment-person-datail ">
                            <strong>司机终:</strong>{{routeInfo.endPlace}}</span>  <!-- 司机终点 -->
                    </div>
                   <span class="icon-telephone right" @click="show_telphoe"></span>
                    <span class="icon-Search right" @click="show_position(endLongitude,endLatitude)"></span>
                </div>
                <!-- <div class="passages-comment-list-bg" :style="{display:item.isShowDetail}">
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
                </div> -->
            </div>
        </div>
    
        <div class="submit-btn" @click.stop="finishRoute" v-if="isDriver">结束行程</div>
    
    </div>
</template>

<script>
import OHeader from '@/components/mine/header.vue'
import Star from '@/components/public/starScore.vue'
import apiHandler from '@/api/services/route.service'
import demandService from '@/api/services/demand.service'
import routeService from '@/api/services/route.service'
import apiDriverHandler from '@/api/services/driver.service'
import { MessageBox } from 'mint-ui';
import Store from '@/utils/store'
export default {
    data() {
        return {
            center: [],
            zoom: 15,
            headerText: "我的行程",
            markers: [],
            routeId: '',
            popupPlate: false,
            isFinished: false,
            isShowMap: 'block',
            compositeScore: 3,
            driverInfo: [
                {
                    isShowDetail: 'none'
                }
            ],
            passagesList: {
                    isShowDetail: 'none'
            }
            ,
            isDriver: false
            ,
            routeInfo: {},
            startPlaceShow: '',
            endLongitude:"",
            endLatitude:"",
            isClose : true
        }
    },
    created: function () {
        this.center = [120.155207, 30.2736900000];
        this.markers.push([120.1552070000, 30.2736900000])
        this.isShowMap = this.isFinished ? 'none' : 'block';
        this.routeId = this.$route.query.routeId;
        let identify = Store.fetch("identity");
        this.getDriverInfo() // 得到司机相关信息
        this.getPassgeerInfo() // 得到用户路线相关信息
        routeService.getRideRoutes(identify=='司机'?1:0, (data) => {
            for (let i = 0; i < data.length; i++) {
                if (data[i].state < 5 && data[i].state!=3)
                   this.routeInfo = data[i];
                    if(data){
                    this.startPlaceShow = this.placeSlice(data[i].startPlace)
                    this.endLongitude = data[i].endLongitude;
                    this.endLatitude = data[i].endLatitude;
                } else{
                       console.log('为空')
                }
            }
            if (this.routeInfo.state == 4 && identify == '司机'){
                this.isDriver = true;
            }
        },
            (res) => {
                if (res.status == '404') {
                    MessageBox('404', '与服务器断开连接');
                } else {
                    MessageBox('获取数据失败');
                }
            });
    },
    components: {
        OHeader,
        Star
    },
    methods: {
        finishRoute: function () {
            apiHandler.finishRoute(this.routeId, success => {
                MessageBox("已经结束行程!");
                this.$router.push({ path: 'home' });
            }, err => {
                MessageBox("取消失败!");
            });
        },
        show_position: function (endLongitude,endLatitude) {
            this.markers = [];
            this.center = [endLongitude, endLatitude];
            this.markers.push([endLongitude, endLatitude]);
        },
        show_model: function (item) {
            for (var i = 0; i < this.passagesList.length; i++)
                this.passagesList[i].isShowDetail = 'none';
                item.isShowDetail = 'block'
        },
        getDriverInfo(){ //得到司机相关信息
             let model = Store.fetch('user');
             apiDriverHandler.getDriverByNo(model,data=>{
                 console.log(data);
                this.driverInfo = data
            },err=>{
                console.log('请求错误')
            });
        },
        getPassgeerInfo(){ //得到用户路线相关信息
            let model = Store.fetch('user');
            demandService.getRideDemandsByRouteId(this.routeId, data => {
            for (let i = 0; i < data.length; i++){
                if(model.userNo === data[i].userNo){
                    this.passagesList = data[i];
                    this.show_position(this.passagesList.endLongitude,this.passagesList.endLatitude)
                }
            }
            }, err => {
                MessageBox("获取服务失败！");
            });
        },
        // 地址过长截取函数 如：浙江省杭州市滨江区浦沿街道火炬大道恒生大厦(园支一路)---》浦沿街道火炬大道恒生大厦(园支一路)
        placeSlice(palceStr) {
            if (palceStr) {
            let w = palceStr.indexOf('区')
            let placeShow = palceStr.substring(w + 1, palceStr.length)
            return placeShow
      }
    },
        show_telphoe:function(){
            MessageBox("请拨打电话:"+this.passagesList.mobile);
        },
        addFriend(item){
            this.$router.push({
            path: '/addFirend', query: {
              userNo: item.userNo
            }
          });
        }
    }
}
</script>

<style>
.comment-carInfo{
    background: #EFEFEF ;
    margin-bottom: 20px;
}
</style>
