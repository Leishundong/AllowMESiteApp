<template>
  <div class="clothes-box">
    <head-bar></head-bar>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  export default {
    components:{
      HeadBar
    },
    data(){
      return{

      }
    },
    methods:{

    },
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .clothes-box{
    height: 100%;
    background: $color-background-big;
  }
</style>
