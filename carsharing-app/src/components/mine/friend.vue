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
                    <span class="icon-telephone right"></span>
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
            friendlist : [
        {
            "indexchar": "A",
            "friends": [
                {
                    "nickname": "赵二狗",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵三胖",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                }
            ]
        },
        {
            "indexchar": "B",
            "friends": [
                {
                    "nickname": "赵二麻子",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                }
            ]
        },
        {
            "indexchar": "Z",
            "friends": [
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                },
                {
                    "nickname": "赵老六",
                    "telphone": "13208899465",
                    "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
                    "worknum": "18780"
                }
            ]
        }
    ],
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
