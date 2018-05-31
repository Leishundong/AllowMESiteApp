<template>
  <div class="img-box">
    <div class="img" v-for="(n,index) in parseInt(6)" @click="selectImg(n)">
      <div class="box"><i class="iconfont icon-weibiaoti1 imgcom" v-if=""></i></div>
      <p><span class="top"><input type="checkbox" class="checkbox" ></span>注意问题</p>
    </div>
    <div style="clear: both"></div>
  </div>
</template>
<script>
  export default {
    name:'DetailsImage',
    data(){
      return{

      }
    },
    methods:{
      selectImg(){

      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .img-box{
    margin-left: px2rem(5);
    background: $color-background-big;
    .img{
      margin-top: px2rem(52);
      float: left;
      width: px2rem(214);
      height: px2rem(206);
      margin-left: px2rem(26);
      background: rgb(206,209,214);
      text-align: center;
      .top{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        .checkbox{
          margin-right: px2rem(10);
          width: px2rem(28);
          height: px2rem(28);
        }
      }
      .box{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        .imgcom{
          @include font(31);
          color: white;
        }
      }
      p{
        @include font(2);
        margin-top: px2rem(120);
      }
    }
  }
</style>
