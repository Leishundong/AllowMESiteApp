<template>
  <div class="body-box" v-if="Isshow">
   <!-- <div class="router-box">
      <div class="router-link" @click="toInbound">
        <div class="center">
          <img class="img" :src="InboundImg"/>
          <p>入站</p>
        </div>
      </div>
      <div class="router-link router-left" @click="toAllOrder">
        <div class="center">
          <img class="img" :src="HangUpImg"/>
          <p>全部订单</p>
        </div>
      </div>
      <div class="router-link router-top" @click="toHangUps">
        <div class="center">
          <img class="img" :src="HangUpImg"/>
          <p>整单上挂</p>
        </div>
      </div>
    </div>-->
    <button @click="toInbound">入站</button>
    <button @click="toHangUps">上挂</button>
    <button @click="toAllOrder">全部订单</button>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  export default {
    data(){
      return{
        InboundImg:'',
        HangUpImg:'',
        SetUpImg:'',
        Isshow:true,
      }
    },
    watch:{
      '$route'(){
        this.toShow()
      }
    },
    created(){
      this.getImg();
      this.toShow()
    },
    methods:{
      getImg(){
        this.InboundImg = imgData.LinkerImg.Inbound.src;
        this.HangUpImg = imgData.LinkerImg.HangUp.src;
        this.SetUpImg = imgData.LinkerImg.SetUp.src;
      },
      toInbound(){
        this.$router.push({ name: 'Inbound', params: { from:'InBound' }});
        this.Isshow = false;
      },
      toAllOrder(){
        this.$router.push({ name: 'AllOrder', params: { from:'AllOrder' }});
        this.Isshow = false;
      },
      /*toHangUp(){
        this.$router.push({ name: 'HangUp', params: { from:'HangUp' }});
      },*/
      toHangUps(){
        this.$router.push({ name: 'HangUps',params: { from:'HangUps' }});
      },
      toShow(){
        if(this.$route.name == 'Home'){
          this.Isshow = true
        }else {
          this.Isshow = false
        }

      }
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss">
  @import "~common/css/mixin";
  @import "~common/css/variable";
  @font-face {
    font-family: MRBlod;
    src: url('../../../static/font/MRBlod.ttf');
  }
  .body-box{
    display: flex;
    flex-direction: column;
    justify-items: center;
    align-items: center;
    background: transparent;
    button{
      margin-top: px2rem(60);
      font-family: MRBlod;
      @include font(17);
      color: white;
      background: $color-background-general;
      height: px2rem(192);
      width: px2rem(694);text-align: center;
      border: none;
      border-radius: px2rem(30);
    }
    /*.router-box{
      margin-left: px2rem(36);
      .router-left{
        margin-left: px2rem(72);
      }
      .router-top{
        margin-top: px2rem(88);
      }
      .router-link{
        float: left;
        text-align: center;
        width: px2rem(304);
        height: px2rem(288);
        background: $color-background-general;
        border-radius: px2rem(50);
        color: white;
        .center{
          position: relative;
          top: 50%;
          transform: translateY(-50%);
          p{
            @include font(17);
            font-family: 黑体;
          }
          .img{
            width: px2rem(216);
            height: px2rem(170);
          }
        }

      }
    }*/
  }
</style>
