<template>
  <div class="list-box" v-if="OrderLister!=''" >
    <div class="list" v-for="(Order,index) in OrderLister" >
      <div :class="getcolor(Order)">
        <span class="top">
           <span class="number">{{Order.number}}</span>
           <span class="payment" v-if="Show"   v-text="Order.payStatus==1?'已付款':'未付款'"></span>
           <span class="fen"  v-if="Show"   v-text="getStatus(Order)+'/'+Order.items.length"></span>
        </span>
      </div>
      <div class="list-body" @click="toDetails(Order)">
        <div class="img-box">
          <div class="top">
            <i class="iconfont icon-yifu general" v-if="Order.id.indexOf('A03') != -1&&Order.type == 1"></i>
            <i class="iconfont icon-bag_icon general" v-if="Order.id.indexOf('A03') != -1&&Order.type == 2"></i>
            <i class="iconfont icon-shafa general" v-if="Order.id.indexOf('A13') != -1"></i>
            <i class="iconfont icon-shangdian general"  v-if="Order.id.indexOf('A10') != -1"></i>
          </div>
        </div>
        <div class="message-box">
          <div class="title">
            <p>预约时间：</p>
            <p>用户姓名：</p>
            <p>收衣地址：</p>
          </div>
          <div class="data">
            <p>{{Order.createtime|formatDate}}</p>
            <p>{{Order.name}}</p>
            <p>{{Order.address}}</p>
          </div>
        </div>
        <div class="fenge"></div>
        <div class="phone-box">
          <img class="img" :src="Dial" @click="clikePhone(Order,event)"/>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  import Data from "../../json/tsconfig.json"
  import SrcData from "../../json/src.json"
  import {formatDate} from '../../common/js/data';
  export default {
    data(){
      return{
        Dial:'',
        Where:'',
        OrderListers:'',
      }
    },
    created(){
      this.getImg();
      this.Where=this.WhereFrom;
    },
    filters: {
      formatDate(time) {
        var date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm');
      }
    },
    computed:{
      Show(){
        if(this.Where=='InBound'){
          return false
        }else {
          return true
        }
      }
    },
    props:{
      WhereFrom:{
        type:String,
        required:true
      },
      OrderLister:{
        type:Array,
        required:true
      }
    },
    methods:{
      getImg(){
        this.Dial=imgData.LinkerImg.Dial.src;
      },
      getcolor(Order){
        let listBar = '';
        if(Order.status == 1){
          listBar = 'list-bar color'
        }else if(Order.status == 3){
          listBar = 'list-bar color1'
        }else{
          listBar = 'list-bar color2';
        }
        return listBar
      },
      clikePhone(data,e){
        this.$alert('马上拨打['+data.phone+']吗?',['确定','取消']).then(()=>{
          window.location.href = 'tel:' + data.phone
        });
        if ( e && e.stopPropagation ) {
          e.stopPropagation();
        } else {
          // 否则，我们需要使用IE的方式来取消事件冒泡
          window.event.cancelBubble = true;
        }
      },
      toDetails(el){
        let Order = el;
        if(this.$route.name == 'AllOrder'){
          console.log(Order);
          console.log(this.Where);
          this.$router.push({ name: 'AllOrderDetails', params: { OrderData:Order,from:this.Where}});
        }else {
          if(this.Where == 'HangUps'){
            this.$router.push({ name: 'HangUpsList', params: { OrderData:Order,from:this.Where}});
          }/*else if(this.Where == 'HangUp'){
          this.$router.push({ name: 'HangUpDe', params: { OrderData:Order,from:this.Where}});
        }*/else{
          console.log(Order);
            if(Order.id.indexOf('A10') != -1){
              this.$router.push({ name: 'MallDetails', params: { OrderData:Order}});
              this.$emit('OrderNumber',Order.number);
            }else {
              this.$emit('OrderNumber',Order.number);
              this.$router.push({ name: 'Details', params: { OrderData:Order,from:this.Where }});
            }
          }
        }
      },
      getStatus(data){
        if(data != null){
          let HangUp = data.items;
          let num = 0;
          HangUp.forEach((item)=>{
            if(Number(item.status)==2){
            }else {
              num += 1;
            }
          });
          return num
        }
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .list-box{
    text-align: center;
    .list{
      width: 100%;
      .list-bar{
        width: 100%;
        height:px2rem(63);
        .top{
           float: left;
           position: relative;
           top: 50%;
           transform: translateY(-50%);
           .payment{
             margin-left: px2rem(141);
             color: #FFF61B;
             @include font(2);
           }
           .fen{
             float:right;
             margin-left: px2rem(132);
             color: white;
             @include font(2);
           }
           .number{
             margin-left: px2rem(14);
             color: white;
           }
         }
      }
      .color{
        background: #00b9ef;
      }
      .color1{
        background: #0032ef;
      }
      .color2{
        background: #8700ef;
      }
      .list-body{
        width: 100%;
        background: $color-background-d;
        height: px2rem(158);
        .img-box{
          float: left;
          position: relative;
          top: 50%;
          transform: translateY(-50%);
          margin-left: px2rem(14);
          background: $color-background-general;
          width: px2rem(87);
          height: px2rem(87);
          border-radius: 50%;
          .top{
            position: relative;
            top: 50%;
            transform: translateY(-50%);
            .general{
              @include font(17);
              color: $color-background-d;
            }
          }
        }
        .message-box{
          float: left;
          margin-left: px2rem(13);
          .title{
            float: left;
            p{
              @include font(-1);
              margin-top: px2rem(20);
              color: #787878;
            }
          }
          .data{
            float: left;
            p{
              @include font(-1);
              text-align: left;
              margin-top: px2rem(20);
              color: #000000;
            }
          }
        }
        .fenge{
          float: right;
          margin-right: px2rem(165);
          padding:px2rem(156) px2rem(6)  px2rem(0) px2rem(6);
          border-left: px2rem(1) solid #DEDEDE;
          font-size: 0;
        }
        .phone-box{
          float: right;
          margin-right: px2rem(-165);
          width: px2rem(165);
          height: 100%;
          .img{
            width: px2rem(92);
            height: px2rem(97);
            position: relative;
            top: 50%;
            transform: translateY(-50%);
          }
        }
      }
    }
  }

</style>
