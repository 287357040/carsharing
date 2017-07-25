//我的好友页
<template>
    <div>
        <OHeader v-bind:headerText="headerText" />
        <div class="friend-container friend-bacgroud">
            <mt-index-list>
                <mt-index-section index="A">
                    <mt-cell >
                        <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                    <mt-cell>
                        <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                    <mt-cell>
                        <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                </mt-index-section>
                <mt-index-section index="B">
                    <mt-cell>
                        <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                    <mt-cell>
                        <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                </mt-index-section>
                <mt-index-section index="Z">
                    <mt-cell>
                       <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                    <mt-cell>
                       <a class="showModel-btn" @click="showPopUp()">
                        <img class="friend-telphone-icon left" src="../../assets/img/touxiang2.png" />
                        <span class="friend-telphone-iconame">赵二狗</span>
                        </a>
                    </mt-cell>
                </mt-index-section>
            </mt-index-list>
        </div>
        <mt-popup  style="height:250px"  v-model="popupVisible" :visible.sync="popupVisible" position="bottom" popup-transition="popup-fade">
            <div class="telphone-detail">
                <img class="telphone-detail-icon" src="../../assets/img/touxiang2.png"/>
                <div class="telphone-detail-iconame">—— 赵二狗 ——</div>
                <div class="other-info">
                    <span >工号：15119</span>
                </div>
                <div class="other-info">
                    <span >联系电话：135 8877 9900 </span>
                    <i class="fa fa-phone right icon"></i>
                </div>
            </div>
        </mt-popup>
    </div>
</template>

<script>
import { IndexList, IndexSection } from 'mint-ui';
import OHeader from '@/components/mine/header.vue'
window.onload = function(){
  document.getElementsByClassName('mint-indexlist-content')[0].style.marginRight ="0px";
};
export default {
    components: {
        IndexList,
        IndexSection,
        OHeader
    },
    data() {
        return {
            popupVisible: false,
            headerText : "我的好友"
        }
    },
    methods: {
        showPopUp() {
            this.popupVisible = true;
        }
    }
}
</script>

<style>

</style>
