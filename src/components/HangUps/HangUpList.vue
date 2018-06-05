<template>
  <div class="clothes-list-box">
    <head-bar v-bind:WhereFrom="WhereFrom"></head-bar>
    <list v-bind:WhereFrom="WhereFrom" v-bind:Items = "Items" v-bind:SelectData="SelectData"  v-bind:OrderId="OrderId" v-if="WhereFrom!=''" ></list>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import List from "../InBound/ClothesList/List.vue"
  import SrcData from  "../../json/src.json"
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
        laundryOrderItemList:[],
        OrderId:'',
        Img:[]
      }
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
          vm.SelectData = to.params.SelectData;
          vm.Items = to.params.Items;
          vm.OrderId = to.params.OrderId;
        }
        if(vm.laundryOrderItemList==''&&vm.OrderId.indexOf("A03")!=-1){
          vm.laundryOrderItemList = [];
          vm.laundryOrderItemList.push(to.params.Obj);
        }else {
          vm.Img = [];
          vm.Img.push(to.params.Obj);
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
      inBound(){
        let src;
        let obj;
        if(this.OrderId.indexOf("A03")==-1){
          obj = this.Img;
          src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.FurnitureOrder.http;
          /* src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.MallOrder.http;
           this.$ajax({
             method:'post',
             url: src,
             headers: {'x-auth-token': this.$token.token},
             params:{ orderid:this.OrderId,waybillimage:obj},
           }).then(res=>{
             console.log(res)
           }).catch(res=>{
             console.log(res)
           })*/
        }else {
          src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.InBound.http;
          obj = this.laundryOrderItemList;
        }
        console.log(JSON.stringify(obj,null,2));
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{ orderid:this.OrderId},
          data: obj
        }).then(res=>{
          console.log(res)
        }).catch(res=>{
          console.log(res)
        })
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .clothes-list-box{
    background: $color-background-big;
    height: 100%;
    .box{
      position: absolute;
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
