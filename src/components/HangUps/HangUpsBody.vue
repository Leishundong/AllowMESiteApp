<template>
  <div class="box">
    <div class="title">
      <p>衣物总计：<span>3</span></p>
      <p>衣物条形码：</p>
    </div>
    <div class="bar-code-box" v-for="n in parseInt(8)">
      <div class="top"><span>{{n}}</span> </div>
      <img class="img" src="../../../static/img/BarCode.png"/>
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
        border: px2rem(1) solid #cccccc;
        margin-bottom: px2rem(33);
      }
      .img{
        margin-top: px2rem(-20);
        margin-left: px2rem(90);
        color: black;
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
