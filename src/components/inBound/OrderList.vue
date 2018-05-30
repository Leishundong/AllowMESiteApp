<template>
  <div class="list-box">
    <div class="list" v-for="Order in OrderLister">
      <div class="list-bar"><div class="top">{{Order['订单编号']}}<span class="payment" v-if="Show">{{Order['付款状态']}}</span><span class="fen" v-if="Show">1/2</span></div></div>
      <div class="list-body" @click="toDetails(Order['订单编号'])">
        <div class="img-box">
         <div class="top">
           <i class="iconfont icon-yifu general" v-if="Order['物品类型']=='衣物'"></i>
           <i class="iconfont icon-bag_icon general" v-if="Order['物品类型']=='包包'"></i>
           <i class="iconfont icon-shafa general" v-if="Order['物品类型']=='家居'"></i>
         </div>
        </div>
        <div class="message-box">
          <div class="title">
            <p>预约时间：</p>
            <p>用户姓名：</p>
            <p>收衣地址：</p>
          </div>
          <div class="data">
            <p>{{Order['预约时间']}}</p>
            <p>{{Order['用户姓名']}}</p>
            <p>{{Order['送衣地址']}}</p>
          </div>
        </div>
        <div class="fenge"></div>
        <div class="phone-box">
          <img class="img" :src="Dial"/>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import imgData from "../../json/img.json"
  import Data from "../../json/tsconfig.json"
  export default {
    data(){
      return{
        Dial:'',
        OrderLister:[],
        Where:''
      }
    },
    created(){
      this.getImg();
      this.getData();
      this.Where=this.WhereFrom;
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
      }
    },
    methods:{
      getImg(){
        this.Dial=imgData.LinkerImg.Dial.src;
      },
      getData(){
        this.OrderLister = Data.linkerName;
      },
      toDetails(el){
        let OrderName = el;
        if(this.Where == 'HangUps'){
        }else {
          this.$router.push({ name: 'Details', params: { OrderName:OrderName,from:this.Where }});
        }
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .list-box{
    margin-top: px2rem(43);
    text-align: center;
    .list{
      width: 100%;
      .list-bar{
        width: 100%;
        height:px2rem(63);
        background: $color-background-general;
        .payment{
          margin-left: px2rem(171);
          color: #FFF61B;
          @include font(2)
        }
        .fen{
          margin-left: px2rem(123);
          color: white;
          @include font(2)
        }
        .top{
          float: left;
          margin-left: px2rem(14);
          position: relative;
          top: 50%;
          transform: translateY(-50%);
        }
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
