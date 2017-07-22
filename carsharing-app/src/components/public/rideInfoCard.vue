<template>
    <div class="driver-issue">
        <div class="msg-more">
            <h3>{{data.listTitle}}</h3>
            <a class="more" v-show="isShowMore">更多
                <i class="fa fa-angle-double-right fa-lg"></i>
            </a>
        </div>
        <ul class="driver-message clearfix">
            <li class="message-li">
                <div class="message-photo">
                    <img src="../../assets/img/touxiang.jpg" alt="photo"></img>
                </div>
                <div class="message-info">
                    <div class="info-row1">
                        <span>龚小敏</span>
                        <i class="fa fa-plus-circle fa-lg plus-circle-style"></i>
                        <i class="fa fa-mars mars-style"></i>
                    </div>
                    <div class="info-row2">
                        <ul class="completeness">
                            <li class="single" :class="{'single-selected': index == data.numIsSeclect}" :key="item" v-for="(item,index) in data.seats"></li>
                        </ul>
                        <p class="completeness-text">
                            <span>1</span>/
                            <span>4</span>
                        </p>
                    </div>
                    <div class="info-row3">
                        <p>7月12日上午7点</p>
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
export default {
    data() {
        return {
            data: {
                listTitle: '司机列表',
                seats: ['1', '2', '3', '4'],
                numIsSeclect: 0,
            },
            isShowMore: true
        }
    }
}
</script>