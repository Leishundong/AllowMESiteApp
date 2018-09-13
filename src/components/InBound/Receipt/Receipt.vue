<template>
  <div class="box" v-if="Items!=''">
    <head-bar></head-bar>
    <print  v-bind:receiptData="receiptData" v-on:Status="getStatus" v-if="ShowPrint"></print>
    <look-remark v-bind:remark="remark" v-if="remarkFlag" v-on:Status="getRemarkStatus"></look-remark>
    <div class="message-box">
      <div class="message">
        <p>交易单号：<span v-text="receiptData.orderNumber"></span></p>
        <p>服务热线：<span v-text="receiptData.servicePhone"></span></p>
        <p>本店地址：<span v-text="receiptData.storeAddress"></span></p>
        <p>服务内容：<span v-text="receiptData.serviceContent"></span></p>
        <p class="top">衣物详情</p>
        <p class="line-top">-----------------------------------------------------</p>
        <p class=""><span>名称/衣物条码</span><span class="price">价格</span></p>
        <div class="clothes">
          <div v-for="item in receiptData.clothes">
            <p><span>{{item.clothesName}}（<span v-text="item.clothesId"></span>）</span><span class="price" v-text="item.clothesPrice"></span></p>
            <p>-瑕疵：<span v-text="item.clothesDefect"></span></p>
            <p>-预计洗后效果：<span v-text="item.washingEffect"></span></p>
          </div>
        </div>
        <p class="line-top">-----------------------------------------------------</p>
        <p class="line-top">总数：<span v-text="receiptData.clothingQuantity"></span><span>件</span></p>
        <p class="top">总价：<span v-text="receiptData.totalPrice"></span><span>元</span></p>
        <p class="line-top">-----------------------------------------------------</p>
        <p>收单时间：<span v-text="receiptData.singleTime"></span></p>
        <p>收单人：<span v-text="receiptData.clerk"></span></p>
        <p>用户姓名：<span v-text="receiptData.customerName"></span></p>
        <p>收单地址：<span v-text="receiptData.customerAddress"></span></p>
        <p>联系方式：<span v-text="receiptData.customerPhone"></span></p>
        <p class="line-top">-----------------------------------------------------</p>
        <div class="msg">
          <p class="title">莫好克温馨提示</p>
          <p>1、凭票取衣，此单遗失请立即到店挂失，以免被他人领取。</p>
          <p>2、超过2000元的贵重衣服请选择保值洗涤。</p>
          <p>3、衣服送洗请自行掏清口袋东西以免发生纠纷。</p>
          <p>4、超过六个月未取取衣物，本店将不通知客户，自行处理。取衣时请当面确认洗涤质量、数量，有异议请当面提出。谢谢合作！</p>
        </div>
      </div>
    </div>
    <div class="height"></div>
    <div class="operate" v-if="IsShow">
      <button class="receipt" @click="checkRemark">查看备注</button><div class="line"></div><button class="receipt left" @click="toReceipt">打印发票</button>
    </div>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import lookRemark from "../../Common/lookRemark.vue"
  import {formatDate} from '../../../common/js/data';
  import Print from './Print.vue'
  export default {
    name:'Receipt',
    components:{
      HeadBar,
      Print,
      lookRemark
    },
    data(){
      return{
        WhereFrom:'',
        Items:'',
        IsShow:'',
        ShowPrint:'',
        clotheList:'',
        remarkFlag:'',
        remark:'',
        receiptData:{}
      }
    },
    beforeRouteEnter(to,from,next){
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.From;
          vm.Items = to.params.Items;
          vm.clotheList = to.params.laundryOrderItemList;
          if(vm.Items.remark!=''&&to.params.remark=='无'){
            vm.remark = vm.Items.remark;
          }else {
            vm.remark = to.params.remark
          }
          vm.getData();
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
      getData(){
        let Items = this.Items;
        let clothesList = this.clotheList;
        let printObj={};
        let clothes = [];
        Items.items.forEach((item)=>{
          clothesList.forEach((clothe)=>{
            if(item.id===clothe.id){
              let defect = this.filterClothesDefect(clothe.flaw);
              let washingEffect = this.filterClothesDefect(clothe.washingEffect);
              clothes.push({
                clothesId:clothe.barCode,
                clothesName:item.laundryProduct.name,
                clothesPrice:(item.laundryProduct.price/100).toFixed(2),
                clothesDefect:defect,
                washingEffect:washingEffect,
              })
            }
          })
        });
        printObj.orderNumber = Items.number;
        printObj.servicePhone='400-0878-315';
        printObj.storeAddress=this.$token.store.address;
        if(Items.type===1){
          printObj.serviceContent='洗衣'
        }else {
          printObj.serviceContent='高端洗护'
        }
        printObj.clothingQuantity = Items.items.length;
        printObj.singleTime = Items.deliveryDate;
        printObj.customerName=Items.name;
        printObj.customerPhone = Items.phone;
        printObj.customerAddress = Items.address;
        printObj.totalPrice = (Items.amount/100).toFixed(2);
        printObj.storeNumber=this.$token.store.number;
        printObj.clothes = clothes;
        printObj.clerk = Items.receiptPeople;
        this.receiptData = printObj;
//TODO 打印输出
        console.log(this.receiptData)
        /* this.$emit('Status','print');*/
      },
      filterClothesDefect(defect){
        let defects = defect.split(',');
        let defectString = '';
        defects.forEach(item=>{
          if(item){
            defectString = defectString+item+'|';
          }
        });
        return defectString.substr(0,defectString.length-1);
      },
      getNowData(){
        let date = new Date();
        let seperator1 = "-";
        let seperator2 = ":";
        let month = date.getMonth() + 1;
        let strDate = date.getDate();
        if (month >= 1 && month <= 9) {
          month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
          strDate = "0" + strDate;
        }
        let currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
          + " " + date.getHours() + seperator2 + date.getMinutes()
          + seperator2 + date.getSeconds();
        return currentdate;
      },
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
      getRemarkStatus(data){
        if(data){
          this.remarkFlag = false
        }
      },
      getStatus(data){
        if(data!=''){
          this.ShowPrint = false;
        }
      },
      toReceipt(){
        this.ShowPrint = true;
      },
      checkRemark(){
        this.remarkFlag = true
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
        padding:0 px2rem(30);
        margin-top: px2rem(20);
        .msg{
          margin-top: px2rem(10);
          width: 100%;
          .title{
            text-align: center;
          }
          p{
            line-height: px2rem(35);
          }
        }
        .clothes{
          margin-top: px2rem(17);
          p{
            line-height: px2rem(30);
          }
        }
        .price{
          float: right;
          margin-right: px2rem(67);
        }
        .top{
          margin-top: px2rem(19);
        }
        .line-top{
          margin-top: px2rem(10);
        }
        p{
          font-family: MRBlod ;
          margin-top: px2rem(5);
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
      width: 100%;
      background: white;
      z-index: 1;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      .line{
        height: 100%;
        width: px2rem(1);
        background: #d1d6ff;
        z-index: 5;
      }
      .receipt{
        color: white;
        float: left;
        width:49.8%;
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
    }
  }
</style>
