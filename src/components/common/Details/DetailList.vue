<template>
  <div class="lists-box">
    <div class="title">
      <p>物流编号:</p>
      <p>物流类型:</p>
      <p>订单编号:</p>
      <p>收单人员:</p>
      <p>用户姓名:</p>
      <p>联系方式:</p>
      <p>送衣地址:</p>
      <p>选择商户:</p>
      <p>衣物类别:</p>
      <p>付款总价:</p>
      <p>付款状态:</p>
      <p>支付类型:</p>
      <p v-if="Where!='InBound'">入站备注:</p>
    </div>
    <div class="data">
      <p v-text="'无'"></p>
      <p v-text="'无'"></p>
      <p v-text="OrderDatas.number"></p>
      <p v-text="'无'"></p>
      <p v-text="OrderDatas.name"></p>
      <p v-text="OrderDatas.phone"></p>
      <p v-text="OrderDatas.address"></p>
      <p v-text="'无'"></p>
      <p v-text="getType(OrderDatas.type)"></p>
      <p v-text="getAmount(OrderDatas.amount)+'元'"></p>
      <p v-text="getPayStatus(OrderDatas.payStatus)"></p>
      <p v-text="getPayType(OrderDatas.payMode)"></p>
      <p v-text="OrderData['入站备注']" v-if="Where!='InBound'"></p>
    </div>
    <div class="dial">
      <p>拨打</p>
    </div>
  </div>
</template>
<script>
  import Data from "../../../json/tsconfig.json"
  export default {
    name:'DetailList',
    data(){
      return{
        OrderDatas:[],
        Where:'',
      }
    },
    props:{
      OrderData:{
        type:Object,
        required:true
      },
      WhereFrom:{
        type:String,
        required:true
      }
    },
    created(){
      this.getData();
    },
    methods:{
      getData(){
        this.Where=this.WhereFrom;
        this.OrderDatas = this.OrderData;
        console.log(this.OrderDatas)
      },
      getType(type){
        let typ = '';
        let types = Number(type);
        if(types==1){
          typ='洗衣';
          return typ
        }else if(types==2){
          typ='高端洗护';
          return typ
        }else if(types==3){
          typ='小让家居';
          return typ
        }else if(types==4){
          typ='小让商城';
          return typ
        }
      },
      getAmount(amount){
        let num = 0;
        num = Number(amount)/100;
        return num;
      },
      getPayType(PayType){
        let type = '';
        let payType = Number(PayType);
        if(payType==1){
          return type='余额支付'
        }else if(payType==0){
          return type='微信支付'
        }
      },
      getPayStatus(payStatus){
        let pay = '';
        let payStatu = Number(payStatus);
        if(payStatu == 1){
          return pay='已支付'
        }else if(payStatu == 0){
          return pay='未支付'
        }
      },
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  @font-face {
    font-family: MRBlod;
    src: url('../../../../static/font/MRBlod.ttf');
  }
  .lists-box{
    margin-top: px2rem(62);
    .title{
      float: left;
      margin-left: px2rem(51);
      p{
        @include font(2);
        margin-bottom: px2rem(20);
        color: rgb(120,120,120);
      }
    }
    .data{
      float: left;
      margin-left: px2rem(36);
      p{
        @include font(2);
        margin-bottom: px2rem(20);
        color: rgb(0,0,0);
      }
    }
    .dial{
      margin-top: px2rem(240);
      float: right;
      margin-right: px2rem(67);
      p{
        @include font(2);
        margin-bottom: px2rem(20);
        font-family: MRBlod ;
        color: $color-background-general;
      }
    }
  }
</style>
