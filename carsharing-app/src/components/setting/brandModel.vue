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
      <mt-index-list >

        <mt-index-section v-bind:index="item.index" v-for="item in carSource" v-bind:key="item">
          <mt-cell class="cell-style" index="item.index" v-for="cars in item.items" v-bind:key="cars" :title="cars.brand" @click.native="choiceBrand(cars.models,cars.brand)"></mt-cell>
        </mt-index-section>
      </mt-index-list>
    </div>
    <div class="model-wrapper" v-show="isShowBrandModel">
      <mt-cell v-bind:title="model.name" :label="model.model" is-link v-for="model in models" v-bind:key="model" @click.native="setBrandAndModel(model.model,models.brand)"></mt-cell>
    </div>
  </div>
</template>

<script>
import Store from '@/utils/store'
export default {
  data: () => {
    return {
      header: {
        headerTitle: '品牌型号'
      },
      isShowBrandModel: false,
      models:[],
      carSource:[
        {index:"B",items:[
          {brand:"宝马",models:[
          {model:'baoma1',name:'一系'},
          {model:'baoma7',name:'七系'}
        ]
        },
         {brand:"奔驰",models:[
          {model:'bechi1',name:'一系'},
          {model:'bechi7',name:'七系'}]
        }]
        },
         {index:"A",items:[
          {brand:"阿里",models:[
          {model:'ali1',name:'一系'},
          {model:'ali7',name:'七系'}
        ]
        },
         {brand:"阿玛尼",models:[
          {model:'amni1',name:'一系'},
          {model:'amani7',name:'七系'}]
        }]
        },
        {index:"L",items:[
          {brand:"雷克沙斯",models:[
          {model:'leikesahshi1',name:'一系'},
          {model:'leikesahshi7',name:'七系'}
        ]
        },
         {brand:"雷蛇",models:[
          {model:'Leishe1',name:'一系'},
          {model:'Leishe7',name:'七系'}]
        }]
        }
      ]
    }
  },
  methods: {
    choiceBrand: function (source,brand) {
      this.isShowBrandModel = true;
      this.models = source;
      this.models.brand = brand;

    },
    goback: function() {
      this.$router.go(-1);
    },
    setBrandAndModel:function(model,brand){
     var car =  Store.fetch("newDriverInfo");
     car.model = model;
     car.brand = brand;
     Store.save("newDriverInfo",car);
     this.$router.go(-1);
    }
  }
}
</script>