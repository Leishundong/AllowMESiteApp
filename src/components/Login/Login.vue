<template>
  <div class="Login">
    <div class="title">
      <img  class="img" :src="LogoImg"/>
      <p class="titles">让我来信息科技有限公司站点App</p>
    </div>
    <login-module></login-module>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  import LoginModule from "./LoginModule.vue"

  export default {
    name: 'Login',
    components:{
      LoginModule
    },
    data(){
      return{
        LogoImg:'',
      }
    },
    created(){
      this.getimg();
      this.$router.push({
        path:this.$route.fullPath
      });
    },
    methods:{
      getimg(){
        this.LogoImg = imgData.LinkerImg.Logo.src;
      }
    }
  }

</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .Login{
    height: 100%;
    background: $color-background-d;
    .title{
      text-align: center;
      .titles{
        color: $login-color;
        @include font(5);
      }
      .img{
        margin-top: px2rem(178);
        width:px2rem(554);
        height: px2rem(153);
      }
    }

  }
</style>
