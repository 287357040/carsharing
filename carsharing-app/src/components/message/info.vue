<template>
    <div class="mine-container mine-container-bgcolor">
        <OHeader v-bind:headerText="headerText" />
        <div class="infolist" v-for="item in messageInfoList" v-bind:key="item" @click="show_detail(item)">
            <div class="icon"></div>
            <div class="info-content">
                <div class="title">
                    <div class="title-icon"></div>{{item.title}}</div>
                <article class="info-text">{{item.content}}</article>
                <div class="info-time">{{item.time}}</div>
            </div>
        </div>
    </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import messageService from '@/api/services/message.service'
export default {
    data() {
        return {
            headerText: "消息列表",
            messageInfoList: []
        }
    }
    ,
    components: {
        OHeader
    },
    methods:
    {
        show_detail(item) {
            this.$router.push({ path: '/details',query:item});
        }
    },
    created: function () {
        messageService.getMessageInfoList((data) => {
            this.messageInfoList = data;
        });

    }
}
</script>
