<template>
  <div class="box" v-if="Items!=''">
    <head-bar></head-bar>
    <print v-bind:Items="Items" v-on:Status="getStatus" v-if="ShowPrint"></print>
    <div class="message-box">
      <div class="message">
        <p>让我来信息科技有限公司</p>
        <p>订单号：<span>{{Items.number}}</span></p>
        <p>服务品牌：<span>{{Items.serviceStore}}</span></p>
        <p>热线电话：<span>122323123123</span></p>

        <p class="top">衣物详情</p>
        <p class="left">------------------------------------------------</p>


        <p class="top">总数：<span>{{Items.items.length}}</span><span>件</span></p>
        <p>总价：<span v-text="getNum()"></span><span>元</span></p>
        <p class="left">------------------------------------------------</p>
        <p>收单时间：<span>{{Items.createtime|formatDate}}</span></p>
        <p>收单人：<span>是D是</span></p>
        <p>条码号：<span>是D是</span></p>
        <p>用户姓名：<span>{{Items.name}}</span></p>
        <p>收单地址：<span>{{Items.address}}</span></p>
        <p>联系方式：<span>{{Items.phone}}</span></p>
        <p class="left">------------------------------------------------</p>
        <p>折扣方式：<span>折扣卷</span></p>
        <p>实收金额：<span>{{Items.amount/100}}</span></p>
        <p>付款方式：<span v-text="getPayType(Items.payMode)"></span></p>
        <p>付款时间：<span>立刻付款</span></p>
        <p>物流备注：<span>无</span></p>
      </div>
    </div>
    <div class="height"></div>
    <div class="operate" v-if="IsShow">
      <button class="receipt">查看备注</button><button class="receipt left" @click="toReceipt">打印发票</button>
    </div>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import {formatDate} from '../../../common/js/data';
  import Print from './Print.vue'
  export default {
    name:'Receipt',
    components:{
      HeadBar,
      Print
    },
    data(){
      return{
        WhereFrom:'',
        Items:'',
        IsShow:'',
        ShowPrint:''
      }
    },
    beforeRouteEnter(to,from,next){
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.From;
          vm.Items = to.params.Items;
          console.log(vm.Items);
          if(vm.WhereFrom == 'HangUp'){
            vm.IsShow = false
          }else {
            vm.IsShow = true
          }
        }
      })
    },
    filters: {
      formatDate(time) {
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm');
      }
    },
    methods:{
      getNum(){
        let num = 0;
        let type = '';
        if(this.Items.id.indexOf('A13')!=-1){
          type = 'furnitureProduct'
        }else {
          type = 'laundryProduct'
        }
        if(this.Items!=''){
          this.Items.items.forEach((item)=>{
            num = num + (Number(item[type].price)/100);
          });
        }
        return num
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
      getStatus(data){
        if(data!=''){
          this.ShowPrint = false;
        }
      },
      toReceipt(){
        this.ShowPrint = true;
      }
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
  .box{
    background: $color-background-big;
    min-height: 100%;
    padding: 0;
    margin: 0;
    .message-box{
      min-height:100%;
      display: flex;
      display:-ms-flex;
      display:-webkit-flex;
      flex-direction: column;
      margin-left: px2rem(70);
      margin-top: px2rem(20);
      padding-bottom: px2rem(30);
      background: white;
      border-radius: px2rem(30);
      width: px2rem(616);
      .left{
        margin-left: px2rem(0);
      }
      .message{
        margin-left: px2rem(60);
        margin-top: px2rem(20);
        .top{
          margin-top: px2rem(50);
        }
        p{
          font-family: MRBlod ;
          margin-top: px2rem(20);
          @include font(-1);
        }
      }
    }
    .height{
      height: px2rem(90);
    }
    .operate{
      position: fixed;
      bottom: px2rem(0);
      .left{
        margin-left: px2rem(3);
      }
      .receipt{
        color: white;
        float: left;
        width: px2rem(373);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
    }
  }
</style>
