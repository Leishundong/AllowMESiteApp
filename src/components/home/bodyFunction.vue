<template>
  <div class="body-box">
    <div class="router-box" v-if="isshow">
      <div class="router-link" @click="toInbound()">
        <div class="center">
          <img :src="InboundImg"/>
          <p>入站</p>
        </div>
      </div>
      <div class="router-link router-left">
        <div class="center">
          <img :src="HangUpImg"/>
          <p>上挂</p>
        </div>
      </div>
      <div class="router-link router-top">
        <div class="center">
          <img :src="HangUpImg"/>
          <p>整单上挂</p>
        </div>
      </div>
      <div class="router-link router-top router-left">
        <div class="center">
          <img :src="SetUpImg"/>
          <p>设置</p>
        </div>
      </div>
    </div>
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
        isshow:true,
      }
    },
    created(){
      this.getImg();
      this.isshow=true;
    },
    methods:{
      getImg(){
        this.InboundImg = imgData.LinkerImg.Inbound.src;
        this.HangUpImg = imgData.LinkerImg.HangUp.src;
        this.SetUpImg = imgData.LinkerImg.SetUp.src;
      },
      toInbound(){
        this.$router.push({ name: 'Inbound', params: { userId:'1' }});
        this.isshow = false;
      }
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss">
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .body-box{
    margin-top: px2rem(121);
    background: transparent;
    .router-box{
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
    }
  }
</style>
