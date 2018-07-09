<template>
  <div class="home-box">
    <head-bar></head-bar>
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
      BodyFunction
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        history.pushState(null, null, document.URL);
      })
    },

    data(){
      return{
        WhereFrom:'',
        BarCode:''
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
