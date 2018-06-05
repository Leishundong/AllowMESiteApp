<template>
  <div class="inBound-box">
    <head-bar v-bind:WhereFrom="WhereFrom"></head-bar>
    <search-bar v-on:SearchData="getSearchData" v-bind:WhereFrom="WhereFrom"></search-bar>
    <div class="tops"><span class="span">待入站订单</span></div>
    <searc-list v-bind:WhereFrom="WhereFrom" v-bind:OrderLister="OrderLister" v-if="OrderLister!=''"></searc-list>
  </div>
</template>
<script>
  import OrderList from "../OrderList.vue";
  import HeadBar from '../../Common/HeadBar.vue'
  import SearchBar from "../../Common/SearchBar.vue"
  import SrcData from "../../../json/src.json"
  import SearcList from "../../Common/SearchList.vue"
  export default {
    components:{
      HeadBar,
      SearchBar,
      SearcList
    },
    data(){
      return{
        WhereFrom:'',
        SearchData:'',
        OrderLister:''
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
          let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
          vm.$ajax({
            methods:"post",
            url: src,
            headers: {'x-auth-token': vm.$token.token},
            params:{
              storeid:vm.$token.accountId
            }
          }).then(res=>{
            console.log(res);
            vm.OrderLister = res.data.data;
          })
        }
      })
    },
    methods:{
      getSearchData(data){
        if(this.WhereFrom=='InBound'){
          this.OrderLister = data.data
        }else {
          this.OrderLister = [];
          let datas = data.data;
          this.OrderLister.push(datas);
          console.log(datas)
        }
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .inBound-box{
    background: $color-background-big;
    height: 100%;
    .tops{
      margin-top: px2rem(75);
      text-align: center;
    }
    .span{
      @include font(11);
      color: #b8b8b8;
    }
  }
</style>
