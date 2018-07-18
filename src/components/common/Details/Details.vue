<template>
  <div class="details-box">
    <head-bar></head-bar>
    <fun-module v-bind:OrderData = "OrderData" v-bind:FinishSelect = "FinishSelect" v-if="WhereFrom!=''"></fun-module>
    <detail-list  v-bind:OrderData = "OrderData" v-if="WhereFrom!=''"></detail-list>
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
        FinishSelect:['测试'],
        isShow:''
      }
    },
    beforeRouteLeave(to,from,next){
      this.WhereFrom = '';
      if(to.name == "Inbound"){
        this.OrderData='';
      }
      next();
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if(from.name == 'Inbound'){
          vm.WhereFrom = '';
          vm.OrderData = '';
          vm.WhereFrom = to.params.from;
          vm.OrderData = to.params.OrderData;
          console.log(vm.OrderData );
          console.log(vm.WhereFrom );
        }else if(from.name=='AddChildren'){
          vm.FinishSelect = [];
          vm.FinishSelect = to.params.SelectData;
        }else if(from.name=='ClothesList'){
          vm.WhereFrom = 'InBound'
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
