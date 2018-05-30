<template>
  <div class="lists-box">
    <div class="title">
      <p>物流编号:</p>
      <p>物流类型:</p>
      <p>订单编号:</p>
      <p>收单人员:</p>
      <p>用户姓名:</p>
      <p>联系方式:</p>
      <p>备用电话:</p>
      <p>送衣地址:</p>
      <p>选择商户:</p>
      <p>衣物类别:</p>
      <p>付款总价:</p>
      <p>付款状态:</p>
      <p>物流备注:</p>
      <p>支付类型:</p>
      <p v-if="Where!='InBound'">入站备注:</p>
    </div>
    <div class="data">
      <p v-text="OrderData['物流编号']"></p>
      <p v-text="OrderData['物流类型']"></p>
      <p v-text="OrderData['订单编号']"></p>
      <p v-text="OrderData['收单人员']"></p>
      <p v-text="OrderData['用户姓名']"></p>
      <p v-text="OrderData['联系方式']"></p>
      <p v-text="OrderData['备用电话']"></p>
      <p v-text="OrderData['送衣地址']"></p>
      <p v-text="OrderData['选择商户']"></p>
      <p v-text="OrderData['衣物类型']"></p>
      <p v-text="OrderData['付款总价']"></p>
      <p v-text="OrderData['付款状态']"></p>
      <p v-text="OrderData['物流备注']"></p>
      <p v-text="OrderData['支付类型']"></p>
      <p v-text="OrderData['入站备注']" v-if="Where!='InBound'"></p>
    </div>
    <div class="dial">
      <p>拨打</p>
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
        OrderData:[],
        Where:'',
      }
    },
    props:{
      OrderNumber:{
        type:String,
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
        let arr = Data.linkerName;
        this.Where=this.WhereFrom;
        arr.forEach((item,index)=>{
          if(item['订单编号']==this.OrderNumber){
            this.OrderData = item;
          }
        });
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
