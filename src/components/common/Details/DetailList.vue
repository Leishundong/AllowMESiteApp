<template>
  <div class="lists-box">
    <div class="item-data">
      <span class="data-title">物流编号:</span>
      <span class="data-data" v-text="OrderDatas.collectMode=2?'无':OrderDatas.id"></span>
    </div>
    <div class="item-data">
      <span class="data-title">物流类型:</span>
      <span class="data-data" v-text="OrderDatas.collectMode=2?'门店':'顺丰'"></span>
    </div>
    <div class="item-data">
      <span class="data-title">订单编号:</span>
      <span class="data-data" v-text="OrderDatas.number"></span>
    </div>
    <div class="item-data">
      <span class="data-title">收单人员:</span>
      <span class="data-data" v-text="OrderDatas.receiptPeople!=null?OrderDatas.receiptPeople:'无'"></span>
    </div>
    <div class="item-data">
      <span class="data-title">用户姓名:</span>
      <span class="data-data" v-text="OrderDatas.name"></span>
    </div>
    <div class="item-data">
      <span class="data-title">联系方式:</span>
      <span class="data-data" v-text="OrderDatas.phone"></span>
    </div>
    <div class="item-data">
      <span class="data-title"  v-text="OrderDatas.id.indexOf('A10')!=-1?'收货地址:':'送衣地址:'"></span>
      <span class="data-data" v-text="OrderDatas.address"></span>
    </div>
    <div class="item-data">
      <span class="data-title">服务商户:</span>
      <span class="data-data" v-text="OrderDatas.serviceStore!=null?OrderDatas.serviceStore:'无'"></span>
    </div>
    <div class="item-data">
      <span class="data-title" v-if="OrderDatas.id.indexOf('A03')!=-1">衣物类别:</span>
      <span class="data-data" v-if="OrderDatas.id.indexOf('A03')!=-1" v-text="getType(OrderDatas.type)"></span>
    </div>
    <div class="item-data">
      <span class="data-title">付款总价:</span>
      <span class="data-data" v-text="getAmount(OrderDatas.amount)+'元'"></span>
    </div>
    <div class="item-data">
      <span class="data-title">付款状态:</span>
      <span class="data-data" v-text="getPayStatus(OrderDatas.payStatus)"></span>
    </div>
    <div class="item-data">
      <span class="data-title">支付类型:</span>
      <span class="data-data" v-text="getPayType(OrderDatas.payMode)"></span>
    </div>
    <div class="item-data">
      <span class="data-title" v-if="false">入站备注:</span>
      <span class="data-data" v-text="OrderData['入站备注']" v-if="false"></span>
    </div>
    <div class="item-data">
      <span class="data-title">物品件数:</span>
      <span class="data-data" v-text="OrderDatas.items.length"></span>
    </div>
    <div class="item-data">
      <span class="data-title">物品名称:</span>
      <span class="data-data data-width" v-text="getName(OrderDatas.items,OrderDatas.id)" ></span>
    </div>
    <!--
    <div class="data">
      <p v-text="OrderDatas.collectMode=2?'无':OrderDatas.id"></p>
      <p v-text="OrderDatas.collectMode=2?'门店':'顺丰'"></p>
      <p v-text="OrderDatas.number"></p>
      <p v-text="OrderDatas.receiptPeople!=null?OrderDatas.receiptPeople:'无'"></p>
      <p v-text="OrderDatas.name"></p>
      <p v-text="OrderDatas.phone"></p>
      <p v-text="OrderDatas.address"></p>
      <p v-text="OrderDatas.serviceStore!=null?OrderDatas.serviceStore:'无'"></p>
      <p v-text="getType(OrderDatas.type)"></p>
      <p v-text="getAmount(OrderDatas.amount)+'元'"></p>
      <p v-text="getPayStatus(OrderDatas.payStatus)"></p>
      <p v-text="getPayType(OrderDatas.payMode)"></p>
      <p v-text="OrderData['入站备注']" v-if="false"></p>
      <p v-text="OrderDatas.items.length"></p>
      <p v-text="getName(OrderDatas.items,OrderDatas.id)"></p>
    </div>
    <div class="title">
      <p>物流编号:</p>
      <p>物流类型:</p>
      <p>订单编号:</p>
      <p>收单人员:</p>
      <p>用户姓名:</p>
      <p>联系方式:</p>
      <p v-text="OrderDatas.id.indexOf('A10')!=-1?'收货地址:':'送衣地址:'"></p>
      <p>服务商户:</p>
      <p v-if="OrderDatas.id.indexOf('A03')!=-1">衣物类别:</p>
      <p>付款总价:</p>
      <p>付款状态:</p>
      <p>支付类型:</p>
      <p v-if="false">入站备注:</p>
      <p>物品件数:</p>
      <p>物品名称:</p>
    </div>
    <div class="dial" @click="clickPhone">
      <p>拨打</p>
    </div>-->
  </div>
</template>
<script>
  import Data from "../../../json/tsconfig.json"
  export default {
    name:'DetailList',
    data(){
      return{
        OrderDatas:[],
      }
    },
    props:{
      OrderData:{
        type:Object,
        required:true
      },
    },
    created(){
      this.getData();
    },
    methods:{
      getData(){
        this.OrderDatas = this.OrderData;
      },
      clickPhone(){
        this.$alert('马上拨打['+this.OrderDatas.phone+']吗?',['确定','取消']).then(()=>{
          window.location.href = 'tel:' + this.OrderDatas.phone
        })
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
      getName(items,id){
        let typeName = '';
        let name = '';
        if(id.indexOf('A03')!=-1){
          typeName = 'laundryProduct';
        }else if(id.indexOf('A10')!=-1){
          typeName = 'mallProduct'
        }else {
          typeName = 'furnitureProduct'
        }
        items.forEach((item,index)=>{
          name = item[typeName].name+',' +name
        });

        console.log(name);
        return name.substring(0,name.length-1);
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
    display: flex;
    flex-direction: column;
    justify-content: center;
    .item-data{
      margin-left: px2rem(51);
      display: flex;
      flex-direction: row;

      .data-title{
        @include font(2);
        margin-bottom: px2rem(20);
        color: rgb(120,120,120);
      }
      .data-data{
        margin-left: px2rem(36);
        @include font(2);
        margin-bottom: px2rem(20);
        color: rgb(0,0,0);
      }
      .data-width{
        width: px2rem(500);
      }
    }

    /*.title{
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
    }*/
  }
</style>
