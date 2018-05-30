<template>
  <div class="inBound-box">
    <span class="span">待入站订单</span>
    <order-list v-bind:WhereFrom="WhereFrom" v-if="WhereFrom!=''"></order-list>
  </div>
</template>
<script>
  import OrderList from "../OrderList.vue";
  export default {
    components:{
      OrderList
    },
    data(){
      return{
        WhereFrom:'',
      }
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Home"){
        this.WhereFrom='';
      }
      next();
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
          console.log(vm.WhereFrom)
        }
      })
    },
    methods:{

    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .inBound-box{
    margin-top: px2rem(66);
    text-align: center;
    .span{
      @include font(11);
      color: #b8b8b8;
    }
  }
</style>
