<template>
  <div class="clothes-list-box">
    <head-bar v-bind:barCode="BarCode" v-bind:WhereFrom="WhereFrom"></head-bar>
    <list v-bind:WhereFrom="WhereFrom" v-bind:Arr="Arr"  v-bind:Items = "Items" v-bind:Remark="Remark" v-bind:SelectData="SelectData"  v-bind:OrderId="OrderId" v-if="WhereFrom!=''" ></list>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import List from "../InBound/ClothesList/List.vue"
  import SrcData from "../../json/src.json"
  export default {
    name:'ClothesList',
    components:{
      HeadBar,
      List
    },
    data(){
      return{
        WhereFrom:'',
        SelectData:['测试'],
        Items:'',
        Arr:[],
        OrderId:'',
        Remark:'',
        BarCode:''
      }
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if(from.name == 'HangUpClothes'){
          console.log(1);
          let src = '';
          if(vm.OrderId.indexOf('A03')==-1){
            src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.FindFurnitureorder.http;
          }else{
            src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.FindClothes.http;
          }
          vm.$ajax({
            method:'post',
            url: src,
            headers: {'x-auth-token': vm.$token.token},
            params:{orderid: vm.OrderId}
          }).then(res=>{
            console.log(res);
            vm.Items = res.data.data.items;
            vm.WhereFrom = 'HangUp';
          }).catch(res=>{
            })
        }else {
          if( vm.WhereFrom==''){
            vm.WhereFrom = to.params.from;
            vm.SelectData = to.params.SelectData;
            vm.Items = to.params.Items.items;
            vm.Remark = to.params.Items.remark;
            vm.OrderId = to.params.OrderId;
          }
        }
      })
    },
    beforeRouteLeave(to,from,next){
      this.WhereFrom = '';
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
    min-height: 100%;
    .box{
      position: fixed;
      bottom: px2rem(5);
      width: 100%;
      .edit{
        color: white;
        float: left;
        width: px2rem(251);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
      .write{
        color: white;
        float: left;
        width: px2rem(253);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
      .inBound{
        color: white;
        float: left;
        width: px2rem(243);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
    }
  }
</style>
