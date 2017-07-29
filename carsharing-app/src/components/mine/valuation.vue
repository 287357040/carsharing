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
        <strong>{{commentTotal}}</strong>条评论</div>
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
      compositeScore:0,
      commentTotal :0,
      commentList: [],
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
