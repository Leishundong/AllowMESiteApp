<template>
  <div class="foot-box">
    <button class="span">下一步</button>
  </div>
</template>
<script>
  export default {
    data(){
      return{

      }
    },
    watch:{
      '$route'(){
        this.BarName();
      }
    },
    methods: {
      BarName() {

      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .foot-box{
    position: absolute;
    bottom: px2rem(20);
    width: 100%;
    .span{
      @include font(5);
      color: white;
      width: 100%;
      height:px2rem(90);
      background: $color-background-general;
    }
  }
</style>
