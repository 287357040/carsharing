//我的好友页
<template>
    <div>
        <OHeader v-bind:headerText="headerText" />
        <div class="friend-container friend-bacgroud">
            <mt-index-list>
                <mt-index-section v-bind:index="item.indexchar" v-for="item in friendlist" v-bind:key="item">
                    <mt-cell v-for="content in item.friends" v-bind:key="content">
                        <a class="showModel-btn" @click="showPopUp(content)">
                            <img class="friend-telphone-icon left" :src="content.imgSrc" />
                            <span class="friend-telphone-iconame">{{content.nickname}}</span>
                        </a>
                    </mt-cell>
                </mt-index-section>
            </mt-index-list>
        </div>
        <mt-popup style="height:250px" v-model="popupVisible" :visible.sync="popupVisible" position="bottom" popup-transition="popup-fade">
            <div class="telphone-detail">
                <img class="telphone-detail-icon" :src="friend_detail_model.imgSrc" />
                <div class="telphone-detail-iconame">—— {{friend_detail_model.nickname}} ——</div>
                <div class="other-info">
                    <span>工号：{{friend_detail_model.worknum}}</span>
                </div>
                <div class="other-info">
                    <span>联系电话：{{friend_detail_model.telphone}} </span>
                    <i class="fa fa-phone right icon"></i>
                </div>
            </div>
        </mt-popup>
    </div>
</template>

<script>
import friendService from '@/api/services/friends.service'
import { IndexList, IndexSection } from 'mint-ui';
import OHeader from '@/components/mine/header.vue'
export default {
    components: {
        IndexList,
        IndexSection,
        OHeader
    },
    data() {
        return {
            popupVisible: false,
            headerText: "我的好友",
            friend_detail_model: {},
            friendlist : [],
        }
    },
    methods: {
        showPopUp(model) {
            this.popupVisible = true;
            this.friend_detail_model = model;
        }
    },
    created: function () {
        friendService.getFriendList((data) => {
                this.friendlist = data;
            });
    }
}
</script>

<style>

</style>
