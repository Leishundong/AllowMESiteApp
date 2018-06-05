<template>
  <div class="details-box">
    <head-bar v-bind:WhereFrom = "WhereFrom"></head-bar>
    <fun-module v-bind:WhereFrom = "WhereFrom" v-bind:OrderData = "OrderData" v-bind:FinishSelect = "FinishSelect" v-if="WhereFrom!=''"></fun-module>
    <detail-list v-bind:WhereFrom = "WhereFrom" v-bind:OrderData = "OrderData" v-if="WhereFrom!=''"></detail-list>
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
        OrderData:'',
        FinishSelect:['测试']
      }
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Inbound"){
        this.WhereFrom='';
        this.OrderData='';
      }
      if(to.name == "HangUp"){
        this.WhereFrom='';
        this.OrderData='';
      }
      next();
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.OrderData==''){
          vm.OrderData = to.params.OrderData;
          vm.WhereFrom = to.params.from;
        }if(from.name=='AddChildren'){
          vm.FinishSelect = [];
          vm.FinishSelect = to.params.SelectData;
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
