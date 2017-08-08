//我的评价
<template>
  <div class="mine-container mine-container-bgcolor">
    <OHeader v-bind:headerText="headerText" style="z-index:20;box-shadow: none;" />
    <div class="valuation-title linear-gradient-bg" style="z-index:20;box-shadow: 0 0px 1px 0 rgba(254, 144, 45, 0.68);;">
      <div class="haed-title">综合评分</div>
      <Star v-bind:score="compositeScore" />
      <div>
  
      </div>
      <div class="haed-title">共有
        <strong>{{commentList.length}}</strong>条评论</div>
    </div>
    <div class="header" :style="{backgroundImage: 'url(' + img + ')'}" />
    <div class="valuation">
      <a v-for="item in commentList" v-bind:key="item">
        <div class="valuation-content" @click="show_detail(item)">
          <img :src="item.imgSrc" />
          <div>
            <span class="nickname">{{item.nickname}}</span>
            <span class="comment">{{item.comment}}</span>
            <Star v-bind:score="compositeScore" style="display:inline-block;" />
            <span class="detail-title right" :style="{display:item.isShowDetail}">综合评分(5.0)</span>
            <div class="comment-star-detail" :style="{display:item.isShowDetail}">
              <div>
                <span class="detail-title left">1、是否守时</span>
                <Star v-bind:score="item.detailScore.time" />
              </div>
              <div>
                <span class="detail-title left">2、车内是否整洁</span>
                <Star v-bind:score="item.detailScore.clean" />
              </div>
              <div>
                <span class="detail-title left">3、文明用语</span>
                <Star v-bind:score="item.detailScore.words" />
              </div>
              <div>
                <span class="detail-title left">4、车技如何</span>
                <Star v-bind:score="item.detailScore.cartech" />
              </div>
            </div>
          </div>
        </div>
      </a>
    </div>
  </div>
</template>
<script>
import OHeader from '@/components/mine/header.vue'
import Star from '@/components/public/starScore.vue'
import commentService from '@/api/services/comment.service'
export default {
  data() {
    return {
      headerText: "我的评价",
      valueDisabled: 2,
      img: require('../../assets/img/toper.png'),
      compositeScore:3,
      commentTotal :2,
      commentList: [
        {
            "nickname": "赵二狗",
            "comment": "秋名山车神，二狗子赛高！",
            "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
            "isShowDetail": "none",
            "detailScore": [
                {
                    "time": 3,
                    "clean": 5,
                    "words": 3,
                    "cartech": 3
                }
            ]
        },
        {
            "nickname": "赵三胖",
            "comment": "秦岭盘山老司机，三胖子最牛逼！",
            "imgSrc": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHQAAABkCAYAAABXTBS8AAABV0lEQVR4nO3ZQQrCQBQFQSd4iOT+p8xqJAsvMCqGpmr/RGgZkD+O45gPMrZ/fwG+S9AYQWMEjRE0RtAYQWO2Odf/hl5b+3vtt/M8lz702lxb+3vtPbkxY9/3OcZYGr9/Gfb32T9XP+yTL2L/u70nN0bQGEFjBI0RNEbQGEFjBI0RNEbQGEFj3ENje/fQ2N6TG+MeGtu7h8b2ntwYQWMEjRE0RtAYQWMEjRE0RtAYQWMEjXEPje3dQ2N7T26Me2hs7x4a23tyYwSNETRG0BhBYwSNETRG0BhBYwSNETTGPTS2dw+N7T25Me6hsb17aGzvyY0RNEbQGEFjBI0RNEbQGEFjBI0RNEbQGPfQ2N49NLb35Ma4h8b27qGxvSc3RtAYQWMEjRE0RtAYQWMEjRE0RtAYQWPcQ2N799DY3pMb4x4a27uHxvae3BhBYwSNETRG0BhBYwSNeQHglqkTdh98+gAAAABJRU5ErkJggg==",
            "isShowDetail": "none",
            "detailScore": [
                {
                    "time": 1,
                    "clean": 2,
                    "words": 1,
                    "cartech": 5
                }
            ]
        }
    ],
    }
  },
  components: {
    OHeader,
    Star
  },
  methods: {
    show_suggest(key) {
      this.$store.dispatch('show_suggest', key)
      this.$router.push({ path: '/mapLocation' })
    },
    show_detail(model) {
      for(var item in this.commentList)
      {
         this.commentList[item].isShowDetail="none";
      }
      model.isShowDetail = "inline-block";
    }
  },
  created: function () {
    commentService.getCommentList((data) => {
      this.commentList = data;
    });
    commentService.getDriverEvaluation((data) => {
      this.compositeScore = data.compositeScore;
       this.commentTotal = data.commentTotal;
    });

  }
}
</script>
