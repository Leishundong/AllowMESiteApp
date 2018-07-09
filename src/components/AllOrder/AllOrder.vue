<template>
  <div class="order-list-box">
    <head-bar></head-bar>
   <order-list class="top" v-bind:OrderLister="OrderLister" v-bind:WhereFrom="WhereFrom" v-if="OrderLister!=''"></order-list>
  </div>
</template>

<script>
  import HeadBar from '../Common/HeadBar.vue'
  import OrderList from '../Common/SearchList.vue'
  import SrcData from "../../json/src.json"
  export default {
    name:'AllOrder',
    components:{
      HeadBar,
      OrderList
    },
    data(){
      return {
        OrderLister:'',
        WhereFrom:''
      }
    },
    created(){
      let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
      this.$ajax({
        method:'post',
        url: src,
        headers: {'x-auth-token': this.$token.token},
        params:{
          storeid:this.$token.accountId
        }
      }).then(res=>{
        if(res.data.data!=''&&res.data.data!=null){
          let allOrder = res.data.data;
          for (let v of allOrder){
            if(v.id.indexOf('A03')==-1){
             if(v.status==4){
               v.status = 3;
             }
            }
          }
          allOrder.sort(this.createComparisonFunction('status'));
          this.OrderLister = allOrder;
        }else {
          this.$alert('获取数据异常，请返回重试').then(()=>{
            this.$router.push({name:'Home'});
          })
        }
      }).catch(res=>{
        this.$alert(res.response.data.msg).then(()=>{
          if(res.response.data.code == '1'){
            this.$router.push({name:'Login'})
          }
        });
      })
    },
   /* beforeRouteEnter(to,from,next){
      next(vm=>{
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
        vm.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': vm.$token.token},
          params:{
            storeid:vm.$token.accountId
          }
        }).then(res=>{
          let allOrder = res.data.data;
          for (let v of allOrder){
            if(v.id.indexOf('A03')==-1){
              v.status = 3;
              console.log(v);
            }
          }
          allOrder.sort(vm.createComparisonFunction('status'));
          vm.OrderLister = allOrder;
        }).catch(res=>{
          console.log(res)
        })
      })
    },*/
    methods:{
      createComparisonFunction(propertyName) {//接收属性名
        return function(object1, object2) {
          let value1 = object1[propertyName];
          let value2 = object2[propertyName];
          //制定比较规则
          if (value1 < value2) {
            return -1;
          } else if (value1 > value2) {
            return 1;
          } else {
            return 0;
          }
        }
      },
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
.order-list-box{
  background: $color-background-big;
  .top{
    margin-top: px2rem(4);
  }
}
</style>
