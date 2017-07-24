<template>
    <div class="driver-issue">
        <div class="msg-more">
            <h3>{{listTitle}}</h3>
            <a class="more" v-show="isShowMore">更多>>
                <i class=""></i>
            </a>
        </div>
        <ul class="driver-message clearfix">
            <li class="message-li clearfix" :key="route" v-for="route in routeVO">
                <div class="message-photo">
                    <img src="../../assets/img/touxiang.jpg" alt="photo"></img>
                </div>
                <div class="message-info">
                    <div class="info-row1">
                        <span>龚小敏</span>
                        <i class="icon-boy"></i>
                        <i class="fa fa-mars mars-style"></i>
                    </div>
                    <div class="info-row2">
                        <ul class="completeness">
                            <li class="single" :class="{'single-selected': index == numIsSeclect}" :key="item" v-for="(item,index) in seats"></li>
                        </ul>
                        <p class="completeness-text">
                            <span>{{route.remainCount}}</span>/
                            <span>{{route.takeCount}}</span>
                        </p>
                    </div>
                    <div class="info-row3">
                        <p>{{route.startTime}}</p>
                        <p>下沙龙湖天街至恒生大厦</p>
                    </div>
                    <div class="info-row4">
                        <span>2017-07-12 15:00:29</span>
                        <p>
                            <i class="fa fa-commenting fa-lg"></i>
                            <span>信息</span>
                        </p>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>
<script>
import allData from '@/api/services/interface.service'
export default {
    data() {
        return {
            listTitle: '司机列表',
            seats: ['1', '2', '3', '4'],
            numIsSeclect: 0,
            routeVO: [],
            isShowMore: true
        }
    },
    created: function () {
        allData.matchRideDemandsByRoute((res) => {
            this.routeVO = res.data.data.routeVO;
            console.log(this.routeVO);
        });
    }
}
</script>