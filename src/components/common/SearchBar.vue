<template>
  <div class="search-box">
    <span class="span-message" v-text="Message"></span>
    <div class="search-lan" v-if="ShowBar">
      <div class="img-box">
        <img class="img" :src="BarCodeImg"/>
      </div>
      <input/>
      <span class="button"><div class="span"><span>搜索</span></div></span>
    </div>
    <div class="search-lan" v-else>
      <input class="add-details"/>
      <div class="img-box">
        <img class="img" :src="BarCodeImg"/>
      </div>
    </div>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  export default {
    name:'SearchBar',
    data(){
      return{
        BarCodeImg:'',
        Message:'',
        ShowBar:'',
      }
    },
    created(){
      this.getImg();
      this.modify();
    },
    watch:{
      '$route'(){
        this.modify();
      }
    },
    methods:{
      getImg(){
        this.BarCodeImg = imgData.LinkerImg.BarCode.src;
      },
      modify(){
        if(this.$route.name=='Home'||this.$route.name=='Inbound'){
          this.Message = '请输入订单编号或手机号'
          this.ShowBar = true;
        }else if(this.$route.name == 'AddDetails'){
          this.Message = '请输入衣物条码';
          this.ShowBar = false;
        }else if(this.$route.name == 'HangUp'||this.$route.name == 'HangUps'){
          this.Message = '请输入衣物编号';
          this.ShowBar = true;
        }
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
      margin-top: px2rem(20);
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
      .add-details{
        width: px2rem(518);
        margin-left: px2rem(0);
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
