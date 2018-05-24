<template>
  <div class="home-box">
    <head-bar></head-bar>
    <serch-bar></serch-bar>
    <body-function></body-function>
    <router-view></router-view>
  </div>
</template>
<script>
  import HeadBar from "../headBar.vue"
  import SerchBar from "../searchBar.vue"
  import BodyFunction from "../bodyFunction.vue"
  export default {
    name:'home',
    components:{
      HeadBar,
      SerchBar,
      BodyFunction
    },
    data(){
      return{

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
    background: #E1F2FC;
    height: 100%;
  }
</style>
