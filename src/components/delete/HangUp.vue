<template>
  <div class="box">
    <head-bar  v-bind:WhereFrom="WhereFrom"></head-bar>
    <div class="body">
      <search-bar v-bind:barCode="BarCode" v-on:SearchData="getSearchData" v-bind:WhereFrom="WhereFrom"></search-bar>
      <searc-list v-bind:WhereFrom="WhereFrom" v-bind:OrderLister="OrderLister" v-if="OrderLister!=''"></searc-list>
    </div>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import SearchBar from "../Common/SearchBar.vue"
  import OrderList from "../InBound/OrderList.vue"
  import Bodys from "../HangUps/HangUpsBody.vue"
  import SrcData from "../../json/src.json"
  import SearcList from "../Common/SearchList.vue"
  export default {
    name:"HangUps",
    components:{
      HeadBar,
      SearchBar,
      OrderList,
      SearcList
    },
    data(){
      return{
        WhereFrom:'',
        SearchData:'',
        OrderLister:'',
        BarCode:''
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
        }
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllHangUp.http;
        vm.$ajax({
          methods:"post",
          url: src,
          headers: {'x-auth-token': vm.$token.token},
          params:{
            storeid:vm.$token.accountId
          }
        }).then(res=>{
          vm.OrderLister = res.data.data;
        }).catch(res=>{
          vm.$alert('登录失效请重新登录').then(()=>{
            vm.$router.push({name:'Login'})
          })
        })
      })
    },
    methods:{
      getSearchData(data){
        this.OrderLister = [];
        let datas = data.data;
        this.OrderLister.push(datas);
      },
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
    .body{
      width: 100%;
    }
  }
</style>
