<template>
  <div class="box">
    <head-bar v-bind:WhereFrom="WhereFrom"></head-bar>
    <search-bar v-on:SearchData="getSearchData" v-bind:WhereFrom="WhereFrom"></search-bar>
    <searc-list v-bind:WhereFrom="WhereFrom" v-bind:OrderLister="OrderLister" v-if="OrderLister!=''"></searc-list>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import SearchBar from "../Common/SearchBar.vue"
  import OrderList from "../InBound/OrderList.vue"
  import SearcList from "../Common/SearchList.vue"
  import SrcData from "../../json/src.json"
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
        OrderLister:''
      }
    },

    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
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
            console.log(vm.OrderLister.data)
          })
        }
      })
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Home"){
        this.WhereFrom='';
      }
      next();
    },
    methods:{
      getSearchData(data){
        this.OrderLister = [];
        let datas = data.data;
        this.OrderLister.push(datas)
        console.log(datas)
      },
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
      position: absolute;
      bottom: px2rem(3);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
