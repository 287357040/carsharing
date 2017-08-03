<template>
    <div>
        <OHeader v-bind:headerText="headerText" />
        <div class="mine-container">
            <section id="route-order">
                <p class="order">
                    <span class="icon-date"></span>
                    <span>{{orderTime}}</span>
                </p>
                <p class="order offset-right">{{orderAddress}}</p>
            </section>
            <section id="driver-card" class="clearfix">
                <div class="driver-photo">
                    <img src="../../assets/img/touxiang.jpg" alt="photo"></img>
                </div>
                <div class="driver-message">
                    <div class="info">
                        <div class="name">
                            <span>阿汤哥</span>
                            <span class="icon-boy"></span>
                        </div>
                        <p class="word">
                            <span>浙ALZ114</span>
                            <span>白色</span>
                            <span>吉利帝豪</span>
                        </p>
                    </div>
                </div>
                <span class="icon-telephone location"></span>
            </section>
            <section id="evaluate-card">
                <div class="evaluate clearfix">
                    <span class="line-left"></span>
                    <h6>他对我的评价</h6>
                    <span class="line-right"></span>
                </div>
            </section>
            <div class="star">
                <Star v-bind:score="compositeScore" />
            </div>
            <section id="evaluate-card">
                <div class="evaluate clearfix">
                    <span class="line-left"></span>
                    <h6>我对他的评价</h6>
                    <span class="line-right"></span>
                </div>
            </section>
            <div class="star">
                <Star v-bind:score="compositeScore" />
            </div>
            <div class="evaluate-style">{{evaluate}}</div>
        </div>
    </div>
</template>

<script>
import OHeader from '@/components/mine/header.vue'
import Star from '@/components/public/starScore.vue'
export default {
    data() {
        return {
            headerText: "我的行程",
            orderTime: '7月12日 上午7点',
            orderAddress: '下沙龙湖天街-恒生大厦',
            compositeScore: 0,
            evaluate: '我感觉司机人好好，很好，很好，很好，重要事情说三遍'

        }
    },
    components: {
        OHeader,
        Star
    }
}
</script>

