<template>
  <div class="details-box">
    <head-bar v-bind:WhereFrom = "WhereFrom"></head-bar>
    <fun-module v-bind:WhereFrom = "WhereFrom" v-bind:OrderData = "OrderData" v-bind:FinishSelect = "FinishSelect" v-if="WhereFrom!=''"></fun-module>
    <detail-list v-bind:WhereFrom = "WhereFrom" v-bind:OrderData = "OrderData" v-if="WhereFrom!=''"></detail-list>
  </div>

</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import FunModule from "../Common/Details/FunctionModule.vue"
  import DetailList from "../Common/Details/DetailList.vue"
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
        if(from.name == 'HangUp'){
          vm.WhereFrom = to.params.from;
          vm.OrderData = to.params.OrderData;
        }else if(from.name=='HangUpList'){
          vm.WhereFrom = 'HangUp'
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
