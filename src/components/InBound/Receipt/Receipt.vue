<template>
  <div class="box">
    <head-bar></head-bar>
    <div class="message-box">
      <div class="message">
        <p>让我来信息科技有限公司</p>
        <p>订单号：<span>122323123123</span></p>
        <p>服务品牌：<span>122323123123</span></p>
        <p>热线电话：<span>122323123123</span></p>

        <p class="top">衣物详情</p>
        <p>------------------------------------------------</p>


        <p class="top">总数：<span>2</span><span>件</span></p>
        <p>总价：<span>0.0</span><span>元</span></p>
        <p>------------------------------------------------</p>
        <p>收单时间：<span>是D是</span></p>
        <p>收单人：<span>是D是</span></p>
        <p>条码号：<span>是D是</span></p>
        <p>用户姓名：<span>撒大声地</span></p>
        <p>收单地址：<span>温州职业技术学院</span></p>
        <p>联系方式：<span>17757726636</span></p>
        <p>------------------------------------------------</p>
        <p>折扣方式：<span>折扣卷</span></p>
        <p>实收金额：<span>300</span></p>
        <p>付款方式：<span>微信</span></p>
        <p>付款时间：<span>立刻付款</span></p>
        <p>物流备注：<span>无</span></p>
      </div>
    </div>
    <foot-bar></foot-bar>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import FootBar from "../../Common/FootBar.vue"
  export default {
    name:'Receipt',
    components:{
      HeadBar,
      FootBar
    },
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
    src: url('../../../../static/font/MRBlod.ttf');
  }
  .box{
    background: $color-background-big;
    height: 100%;
    .message-box{
      margin-top: px2rem(30);
      margin-left: px2rem(68);
      background: white;
      border-radius: px2rem(30);
      width: px2rem(616);
      height: px2rem(1062);
      .message{
        float: left;
        margin-left: px2rem(60);
        margin-top: px2rem(40);
        .top{
          margin-top: px2rem(50);
        }
        p{
          font-family: MRBlod ;
          margin-top: px2rem(20);
          @include font(-1);
        }
      }
    }
  }
</style>
