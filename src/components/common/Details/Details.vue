<template>
  <div class="details-box">
    <head-bar></head-bar>
    <fun-module v-bind:WhereFrom = "WhereFrom" v-bind:OrderNumber = "OrderNumber" v-if="WhereFrom!=''"></fun-module>
    <detail-list v-bind:WhereFrom = "WhereFrom" v-bind:OrderNumber = "OrderNumber" v-if="WhereFrom!=''"></detail-list>
  </div>

</template>
<script>
  import HeadBar from "../HeadBar.vue"
  import FunModule from "./FunctionModule.vue"
  import DetailList from "./DetailList.vue"
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
    beforeRouteLeave(to,from,next){
      if(to.name == "Inbound"){
        this.WhereFrom='';
        this.OrderNumber='';
      }
      next();
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.OrderNumber==''){
          vm.OrderNumber = to.params.OrderName;
          vm.WhereFrom = to.params.from;
          console.log(1)
        }
      })
    },
    methods:{
      getParmes(){
        this.WhereFrom=this.$route.params.from;
        this.OrderNumber=this.$route.params.OrderName;
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
