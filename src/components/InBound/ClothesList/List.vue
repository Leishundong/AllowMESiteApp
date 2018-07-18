<template>
  <div class="list-box">
    <div class="message">
      <p>总数<span v-text="Total"></span></p>
      <p>总价<span v-text="Num"></span></p>
      <p>入站备注<span  v-text="Remark"></span></p>
    </div>
    <div :class="getcolor(index)?'color list':'list'"  v-for="(n,index) in ItemData" @click="toAddDetails(n)">
      <div class="img-box">
        <div class="top">
          <i class="iconfont icon-yifu img" v-if="orderID.indexOf('A03') != -1"></i>
          <i class="iconfont icon-shafa img" v-if="orderID.indexOf('A13') != -1"></i>
        </div>
      </div>
      <div v-if="Show">
        <span>{{n.laundryProduct.name}}</span><span class="right">{{Number(n.laundryProduct.price)/100}}元</span>
        <span class="tops" v-text="getType(n.laundryProduct.category)"></span>
      </div>
      <div v-if="!Show">
        <span>{{n.furnitureProduct.name}}</span><span class="right">{{Number(n.furnitureProduct.price)/100}}元</span>
        <span class="tops" v-text="getType(n.furnitureProduct.category)"></span>
      </div>
    </div>
    <!--<div class="list"  v-for="n in ItemData" @click="toHangUpClothes(n)" v-if="WhereFrom == 'HangUp'">
      <div class="img-box">
        <div class="top">
          <i class="iconfont icon-yifu img" v-if="orderID.indexOf('A03') != -1"></i>
          <i class="iconfont icon-shafa img" v-if="orderID.indexOf('A13') != -1"></i>
        </div>
      </div>
      <div v-if="Show">
        <span>{{n.barCode}}</span><span class="right" v-text="n.status==2?'已上挂':'未上挂'"></span>
        <span class="tops" v-text="getType(n.laundryProduct.category)"></span>
      </div>
      <div v-if="!Show">
        <span>{{n.barCode}}</span><span class="right" v-text="n.status==2?'已上挂':'未上挂'"></span>
        <span class="tops" v-text="getType(n.furnitureProduct.category)"></span>
      </div>
    </div>-->
  </div>
</template>
<script>
  export default {
    name:'List',
    data(){
      return{
        Num:'',
        Total:'',
        ItemData:[],
        Show:'',
        orderID:''
      }
    },
    props:{
      SelectData:{
        type:Array,
        required:true
      },
      Items:{
        type:Array,
        required:true
      },
      OrderId:{
        type:String,
        required:true
      },
      Remark:{
        type:String,
        required:true
      },
      Arr:{
        type:Array,
        required:true
      }
    },
    created(){
      this.SelectDatas = this.SelectData;
      this.ItemData = this.Items;
      this.orderID = this.OrderId;
      if(this.orderID.indexOf("A03")==-1){
        this.Show = false
      }else {
       this.Show = true
      }
      this.getData();
    },
    /*computed:{*/
     /* ShowList(){
        if(this.WhereFrom=='HangUp'){
          return true
        }else {
          return false
        }
      },
      Show(){
        if(this.WhereFrom=='Inbound'){
          return true
        }else {
          return false
        }
      }
    },*/
    methods:{
      getData(){
        this.Total = this.ItemData.length;
        let num = 0;
        if(this.orderID.indexOf("A03")==-1){
          this.ItemData.forEach((item,index)=>{
            num += Number(item.furnitureProduct.price)/100;
          });
        }else {
          this.ItemData.forEach((item,index)=>{
            num += Number(item.laundryProduct.price)/100;
          });
        }
        this.Num = num;
      },
      getType(type){
        let typ = '';
        let types = Number(type);
        if(types==0){
          typ='上衣类';
          return typ
        }else if(types==1){
          typ='裤裙类';
          return typ
        }else if(types==2){
          typ='皮草类';
          return typ
        }else if(types==3){
          typ='装饰类';
          return typ
        }else if(types==4){
          typ='鞋包类';
          return typ
        }
      },
      getcolor(index){
        let type = false;
        console.log(this.Arr);
        if(this.Arr!=[]&&this.Arr!=null){
          this.Arr.forEach((item)=>{
            if(item.id==this.ItemData[index].id){
              type = true;
              return type
            }
          })
        }
        return type;
      },
      toAddDetails(item){
        let orderId = this.OrderId;
        let arr = this.Arr;
        this.$router.push({name:'AddDetails',params:{Item:item,OrderId:orderId,Arr:arr}})
      },
      toHangUpClothes(HangUpItem){
        let orderId = this.OrderId;
        this.$router.push({name:'HangUpClothes',params:{HItem:HangUpItem,OrderId:orderId}})
      },
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .list-box{
    margin-left: px2rem(17);
    .message{
      .width{
        width: px2rem(300);
      }
      p{
        margin-top: px2rem(30);
        @include font(2);
        color: black;
        span{
          float: right;
          margin-right: px2rem(400);
        }
      }
    }
    .color{
      color: $color-background-general;
    }
    .list{
      margin-top: px2rem(40);
      height: px2rem(109);
      width: 100%;
      span{
        @include font(1);
        position: absolute;
        margin-left: px2rem(103);
        margin-top: px2rem(23);
      }
      .right{
        margin-left: px2rem(432);
      }
      .tops{
        padding-top: px2rem(48);
      }
      .img-box{
        float: left;
        width: px2rem(109);
        height: 100%;
        background: rgb(23,28,96);
        text-align: center;
        .top{
          position: relative;
          top: 50%;
          transform: translateY(-50%);
          .img{
            color: white;
            @include font(20)
          }
        }
      }
    }
  }
</style>
