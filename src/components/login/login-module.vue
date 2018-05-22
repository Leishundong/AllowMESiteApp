<template>
  <div class="Login-box">
    <span>工号:</span><input/>
    <div class="line"></div>
    <span>密码:</span><input type="password"/>
    <button class="button">登录</button>
  </div>
</template>
<script>

</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .Login-box{
    margin-left: px2rem(63);
    margin-top: px2rem(113);
    width: px2rem(625);
    .line{
      border: #242424 px2rem(1) solid ;
      margin-top: px2rem(38);
      margin-bottom: px2rem(38);
    }
    input{
      text-align: center;
      outline:none;
      @include font(5);
    }
    span{
      color: $login-color;
      @include font(5);
    }
    .button{
      background: $login-color;
      text-align: center;
      color: #ffffff;
      @include font(5);
      margin-top: px2rem(107);
      width: px2rem(654);
      height: px2rem(128);
    }
  }
</style>
