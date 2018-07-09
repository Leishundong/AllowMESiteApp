<template>
  <div class="box">
    <div class="title">
      <p>衣物总计：<span>{{ItemData.items.length}}</span></p>
      <p>衣物条形码：</p>
    </div>
    <div class="bar-code-box" v-for="(n,index) in ItemData.items">
      <div class="top"><span>{{index+1}}</span></div>
      <span class="span">{{n.barCode}}</span>
      <div class="line"></div>
    </div>
  </div>
</template>
<script>
  export default {
    name:'HangUpsBody',
    data(){
      return{

      }
    },
    props:{
      ItemData:{
        type:Object,
        required:true
      }
    },
    methods:{

    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  @font-face {
    font-family: MRBlod;
    src: url('../../../static/font/MRBlod.ttf');
  }
  .box{
    .title{
      margin-left: px2rem(40);
      p{
        @include font(2);
        color: black;
        margin-top: px2rem(30);
        font-family: MRBlod;
      }
    }
    .bar-code-box{
      height: px2rem(100);
      margin-top: px2rem(39);
      margin-left: px2rem(40);
      .line{
        width: 100%;
        margin-top: px2rem(30);
        border: px2rem(1) solid #cccccc;
      }
      .span{
        @include font(6);
        margin-left: px2rem(60)
      }
      .top{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        span{
          @include font(2);
        }
      }

    }
  }
</style>
