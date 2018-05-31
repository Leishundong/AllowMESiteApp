<template>
  <div class="clothes-list-box">
    <head-bar></head-bar>
    <list v-bind:WhereFrom="WhereFrom" v-bind:Items = "Items" v-bind:SelectData="SelectData" v-if="WhereFrom!=''"></list>
    <foot-bar v-bind:SelectData="SelectData" v-if="WhereFrom!='HangUp'"></foot-bar>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import FootBar from "../../Common/FootBar.vue"
  import List from "./List.vue"
  export default {
    name:'ClothesList',
    components:{
      HeadBar,
      FootBar,
      List
    },
    data(){
      return{
        WhereFrom:'',
        SelectData:['测试'],
        Items:'',
      }
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
          vm.SelectData = to.params.SelectData;
          vm.Items = to.params.Items;
          console.log(vm.Items)
        }
      })
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Details"){
        this.WhereFrom='';
      }
      next();
    },
    methods:{
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .clothes-list-box{
    background: $color-background-big;
    height: 100%;
  }
</style>
