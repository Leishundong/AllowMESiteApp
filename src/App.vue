<template>
  <div id="app" style="height: 100%">
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss" rel="stylesheet/scss">
  @import "~common/css/base";
  @import "../static/img/iconfont/iconfont.css";
  @font-face{
    font-family: 微软雅黑常规;
    src: url('../static/font/MicrosoftYaHei.ttf');
  }
  body{
    font-family: $General-font;
  }
</style>
