<template>
  <div @click.stop @touchmove.prevent class="confirms">
    <div class="confirms-container zh-center" >
      <div class="content">
        <span class="msg">请选择获取图片方式</span>
      </div>
      <div class="button-box">
        <span class="button" @click="camera">拍照</span>
        <span class="button" @click="photo">相册</span>
        <span class="button" @click="cancels">取消</span>
      </div>
    </div>
  </div>
</template>
<script>
  import SrcData from "../../json/src.json"
  export default {
    data(){
      return {
        Remarks:false,
        Msg:'点击此处输入备注',
        Remark:''
      }
    },
    methods: {
      camera(){
        this.$emit('Status','camera');
      },
      photo(){
        this.$emit('Status','photo');
      },
      cancels(){
        this.$emit('Status','cancel');
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
      @include px2rem(height, 174);
      background: #fff;
      border: px2rem(2) solid $color-line;
      @include px2rem(border-radius, 15);
      .content {
        display: flex;
        margin-top: px2rem(30);
        width: 100%;
        @include px2rem(height, 61);
        @include font(8);
        box-sizing: border-box;
        border-bottom: px2rem(4) solid $color-line;
        .msg{
          margin-left: px2rem(30);
        }
      }
      .button-box {
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
      }
    }
  }
</style>
