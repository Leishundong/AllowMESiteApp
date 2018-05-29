<template>
  <div class="img-box">
    <div class="img" v-for="n in parseInt(6)">
      <div class="box"><i class="iconfont icon-weibiaoti1 imgcom"></i></div>
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

    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .img-box{
    margin-left: px2rem(5);
    .img{
      margin-top: px2rem(32);
      float: left;
      width: px2rem(214);
      height: px2rem(206);
      margin-left: px2rem(26);
      background: rgb(206,209,214);
      text-align: center;
      .box{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        .imgcom{
          @include font(31);
          color: white;
        }
      }
    }
  }
</style>
