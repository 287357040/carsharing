<template>
  <div class="owner-wrapper">
    <div class="navigator-wrapper linear-gradient-bg">
      <div class="navigator">
        <div class="nav-wrapper">
          <div class="avatar-wrapper" @click="goback">
            <div class="avatar">
              <i class="icon-return"></i>
            </div>
          </div>
          <div class="tab-wrapper">
            <p class="header-text">{{header.headerTitle}}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="brand-wrapper">
      <mt-index-list>
        <mt-index-section index="A">
          <mt-cell class="cell-style" title="Aaron" @click.native="choiceBrand"></mt-cell>
          <mt-cell title="Alden"></mt-cell>
          <mt-cell title="Austin"></mt-cell>
        </mt-index-section>
        <mt-index-section index="B">
          <mt-cell title="Baldwin"></mt-cell>
          <mt-cell title="Braden"></mt-cell>
        </mt-index-section>
        ...
        <mt-index-section index="Z">
          <mt-cell title="Zack"></mt-cell>
          <mt-cell title="Zane"></mt-cell>
        </mt-index-section>
      </mt-index-list>
    </div>
    <div class="model-wrapper" v-show="isShowBrandModel">
  
      </div>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      header: {
        headerTitle: '品牌型号'
      },
      isShowBrandModel: false
    }
  },
  methods: {
    choiceBrand: function () {
      this.isShowBrandModel = true;
    },
    goback: function() {
      this.$router.go(-1);
    }
  }
}
</script>