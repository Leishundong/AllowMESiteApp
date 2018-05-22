<template>
  <div class="bar-box">
    <div class="left-button" v-if="isshow">
      <span v-text="leftbutton"></span>
      <span class="title">功能菜单</span>
    </div>
    <div class="left-button" v-if="!isshow">
      <span  v-text="leftbutton"></span>
      <span class="title">功能菜单</span>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        isshow:true,
        leftbutton:'注销',
      }
    },
    created(){
    },
    watch:{
      '$route'(){
        if(this.$route.name == 'home'){
          this.isshow = true;
          this.leftbutton = '注销'
        }else {
          this.isshow = false;
          this.leftbutton = '返回'
        }
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .bar-box{
    width: 100%;
    height: px2rem(90);
    background: $color-background-general;
    color: white;
    .left-button{
      position: relative;
      top: 50%;
      transform: translateY(-50%);
      @include font(5);
      margin-left: px2rem(17);
      .title{
        width: 50%;
        margin-left: 25%;
        @include font(5);
      }
    }

  }
</style>
