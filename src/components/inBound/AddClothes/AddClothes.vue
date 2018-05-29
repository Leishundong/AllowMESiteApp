<template>
  <div class="clothes-box">
    <head-bar></head-bar>
    <clothes-list v-on:Select-Data="getSelect"></clothes-list>
    <foot-bar v-bind:SelectData = "SelectData"></foot-bar>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import ClothesList from "./ClothesList.vue"
  import FootBar from "../../Common/FootBar.vue"
  export default {
    components:{
      HeadBar,
      ClothesList,
      FootBar
    },
    data(){
      return{
        SelectData:'',
      }
    },
    methods:{
      getSelect(Data){
        if(Data!=''){
          this.SelectData = Data;
        }
      }
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
