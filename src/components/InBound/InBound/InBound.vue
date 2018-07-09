<template>
  <div class="inBound-box">
    <head-bar></head-bar>
    <search-bar  v-bind:barCode="BarCode" v-on:SearchData="getSearchData" v-bind:WhereFrom="WhereFrom"></search-bar>
    <div class="tops"><span class="span">待入站订单</span></div>
    <searc-list class="list-top" v-bind:WhereFrom="WhereFrom" v-bind:OrderLister="OrderLister" v-on:OrderNumber="getOrderNumber" v-if="OrderLister!=''"></searc-list>
  </div>
</template>
<script>
  import OrderList from "../OrderList.vue"
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
        OrderLister:[],
        BarCode:'',
        OrderNumber:''
      }
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Home"){
        this.WhereFrom='';
        this.OrderLister=[];
      }
      next();
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
        }
        if(from.name == 'ClothesList'||from.name == 'MallDetails'){
         let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.SearchOrders.http;
          vm.$ajax({
            method:'post',
            url: src,
            headers: {'x-auth-token': vm.$token.token},
            params:{
              key:vm.OrderNumber,
              type:0
            }
          }).then(res=>{
            if(res.data.data == ''){
              vm.OrderLister.forEach((item,index)=>{
                if(item.number == vm.OrderNumber){
                  vm.OrderLister.splice(index,1);
                }
              });
            }else if(res.data.data != ''){
              this.$alert('入站失败，请重试')
            }else {
              this.$alert('获取数据异常，请返回后重试').then(()=>{
                this.$router.push({name:'Home'})
              })
            }
          }).catch(res=>{
            this.$alert(res.response.data.msg).then(()=>{
              if(res.response.data.code == '1'){
                this.$router.push({name:'Login'})
              }
            });
            })
        }
        /*let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
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
    methods:{
      getSearchData(data){
        if(this.WhereFrom=='InBound'){
          this.OrderLister = data.data;
        }else {
          this.OrderLister = [];
          let datas = data.data;
          this.OrderLister.push(datas);
        }
      },
      getOrderNumber(data){
        this.OrderNumber = data;
      },
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
    .list-top{
      margin-top: px2rem(43);
    }
    .span{
      @include font(11);
      color: #b8b8b8;
    }
  }
</style>
