<template>
  <div class="search-box">
    <span class="span-message">请输入订单编号或手机号</span>
    <div class="search-lan">
      <div class="img-box">
        <img class="img" :src="BarCodeImg"/>
      </div>
      <input/>
      <span class="button"><div class="span"><span>搜索</span></div></span>
    </div>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  export default {
    name:'searchBar',
    data(){
      return{
        BarCodeImg:''
      }
    },
    created(){
      this.getImg();
    },
    methods:{
      getImg(){
        this.BarCodeImg = imgData.LinkerImg.BarCode.src;
      }
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .search-box{
    margin-left: px2rem(36);
    margin-top: px2rem(72);
    .span-message{
      @include font(5);
      color: $General-font-color;
    }
    .search-lan{
      height: px2rem(73);
      .img-box{
        float: left;
        width: px2rem(165);
        height: px2rem(73);
        background: $color-background-general;
        .img{
          margin-left: px2rem(33);
          margin-top: px2rem(4);
          width: px2rem(100);
          height: px2rem(60);
        }
      }
      input{
        float: left;
        @include font(5);
        text-indent: px2rem(12);
        margin-left: px2rem(5);
        width: px2rem(343);
        height: 100%;
      }
      .button{
        float: left;
        text-align: center;
        margin-left: px2rem(5);
        width: px2rem(165);
        height: px2rem(73);
        background: $color-background-general;
        color: white;
        @include font(5);
        .span{
          position: relative;
          top: 50%;
          transform: translateY(-50%);
        }
      }
    }
  }
</style>
