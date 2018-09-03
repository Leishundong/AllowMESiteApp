<template>
  <div @click.stop @touchmove.prevent class="confirms">
    <div class="confirms-container zh-center" >
      <div class="content">
        <span class="msg">备注</span>
        <i class="iconfont icon-guanbi icon-class" @click="remarkCancel"></i>
      </div>
      <div class="remark">
        <div class="remark-box">
          <span v-text="this.remark"></span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        Remark:''
      }
    },
    props:{
      remark:{
        type:String
      }
    },
    methods: {
      remarkCancel(){
        this.$emit('Status',true);
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";

  $color-line: #EAEAEB;
  .confirms{
    z-index: 9998;
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: $color-background-cover;
    .confirms-container {
      @include px2rem(width, 563);
      @include px2rem(height, 224);
      background: #fff;
      border: px2rem(2) solid $color-line;
      @include px2rem(border-radius, 15);
      .content {
        display: flex;
        margin-top: px2rem(30);
        width: 100%;
        @include px2rem(height, 61);
        @include font(5);
        padding: 0 px2rem(30);
        box-sizing: border-box;
        border-bottom: px2rem(4) solid $color-line;
        justify-content: space-between;
      }
      .remark{
        .remark-box{

        }
        display: flex;
        width: 100%;
        height:px2rem(135);
        align-items: center;
        justify-content: center;
        @include font(6)
      }
     /* .button-box {
        display: flex;
        .button {
          display: flex;
          justify-content: center;
          align-items: center;
          flex-grow: 1;
          @include px2rem(height, 83);
          @include font(3);
          &:nth-child(1) {
            color: $color-theme;
          }
          &:nth-child(2) {
            color: $color-theme;
            box-sizing: border-box;
            border-left: px2rem(4) solid $color-line;
          }
          &:nth-child(3) {
            box-sizing: border-box;
            border-left: px2rem(4) solid $color-line;
          }
        }
      }*/
    }
  }
</style>
