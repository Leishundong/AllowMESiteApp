<template>
  <div class="home-box">
    <head-bar></head-bar>
    <serch-bar v-bind:WhereFrom="WhereFrom"></serch-bar>
    <body-function></body-function>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import SerchBar from "../../Common/SearchBar.vue"
  import BodyFunction from "../BodyFunction.vue"
  import SrcData from "../../../json/src.json"
  export default {
    name:'Home',
    components:{
      HeadBar,
      SerchBar,
      BodyFunction
    },
    created(){
    },
    data(){
      return{
        WhereFrom:'测试'
      }
  },
  methods:{

  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .home-box{
    background: $color-background-big;
    height: 100%;
  }
</style>
