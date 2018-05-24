<template>
  <div class="details-box">
    <head-bar></head-bar>
    <fun-module v-bind:WhereFrom = "WhereFrom" v-bind:OrderNumber = "OrderNumber"></fun-module>
    <detail-list v-bind:WhereFrom = "WhereFrom" v-bind:OrderNumber = "OrderNumber"></detail-list>
  </div>

</template>
<script>
  import HeadBar from "../home/headBar.vue"
  import FunModule from "./functionModule.vue"
  import DetailList from "./detailList.vue"
  export default {
    components:{
      HeadBar,
      FunModule,
      DetailList
    },
    data(){
      return{
        WhereFrom:'',
        OrderNumber:'',
      }
    },
    created(){
      this.getParmes();
    },
    methods:{
      getParmes(){
        this.WhereFrom=this.$route.params.from;
        this.OrderNumber=this.$route.params.OrderName;
        console.log(this.WhereFrom);
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .details-box{
    height: 100%;
    background:$color-background-big;
  }
</style>
