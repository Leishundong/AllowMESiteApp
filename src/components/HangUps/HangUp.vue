<template>
  <div class="box">
    <head-bar></head-bar>
    <search-bar></search-bar>
    <order-list v-bind:WhereFrom="WhereFrom" v-if="WhereFrom!=''"></order-list>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import SearchBar from "../Common/SearchBar.vue"
  import OrderList from "../InBound/OrderList.vue"
  import Bodys from "./HangUpsBody.vue"
  export default {
    name:"HangUps",
    components:{
      HeadBar,
      SearchBar,
      OrderList,
      Bodys
    },
    data(){
      return{
        WhereFrom:''
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
      okToHangUp(){

      }
    },
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .box{
    background: $color-background-big;
    height: 100%;
    .button{
      color: white;
      position: absolute;
      bottom: px2rem(3);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
