<template>
  <div class="box">
    <head-bar></head-bar>
    <search-bar  v-bind:barCode="BarCode"  v-on:SearchData="getSearchData" v-on:Search="getSearch" v-bind:WhereFrom="WhereFrom"></search-bar>
    <search-list class="list-top" v-bind:WhereFrom="WhereFrom"  v-bind:OrderLister="OrderLister" v-if="WhereFrom!=''"></search-list>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import SearchBar from "../Common/SearchBar.vue"
  import OrderList from "../InBound/OrderList.vue"
  import SearchList from "../Common/SearchList.vue"
  import SrcData from "../../json/src.json"
  export default {
    name:"HangUps",
    components:{
      HeadBar,
      SearchBar,
      OrderList,
      SearchList
    },
    data(){
      return{
        WhereFrom:'',
        SearchData:'',
        OrderLister:[],
        BarCode:'',
        OrderNumber:'',
        Search:''
      }
    },

    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
          console.log(vm.WhereFrom)
        }
        if(from.name == 'HangUpsList'){
          console.log(vm.Search)
          let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.SearchBars.http;
          vm.$ajax({
            method:'post',
            url: src,
            headers: {'x-auth-token': vm.$token.token},
            params:{
              barcode:vm.Search
            }
          }).then(res=>{
            console.log(res);
            if(res.data.data!=''&&res.data.data!=null){
              vm.OrderLister = res.data.data;
            }else if(res.data.data == ''&&res.data.data!=null){
              vm.OrderLister = [];
            }else {
              vm.$msg.setShow('数据异常,请重试')
            }
          }).catch(res=>{
            vm.$alert(res.response.data.msg).then(()=>{
              if(res.response.data.code == '1'){
                vm.$router.push({name:'Login'})
              }
            });
          })
        }
        /*let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllHangUp.http;
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
        })*/
      })
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Home"){
        this.WhereFrom='';
        this.OrderLister=[];
      }
      next();
    },
    methods:{
      getSearchData(data){
        this.OrderLister = [];
        this.OrderLister = data.data;
      },
      getSearch(data){
        console.log(data);
        this.Search = data;
        console.log(this.Search);
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
      position: absolute;
      bottom: px2rem(3);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
    .list-top{
      margin-top: px2rem(43);
    }
  }
</style>
