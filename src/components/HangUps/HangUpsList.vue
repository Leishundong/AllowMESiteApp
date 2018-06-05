<template>
  <div class="box">
    <head-bar></head-bar>
    <search-bar></search-bar>
    <div class="list" v-if="ItemData!=''">
      <div class="list-bar"><div class="top"><span v-text="ItemData.number"></span><span class="payment" v-text="ItemData.payStatus==1?'已付款':'未付款'"></span><span class="fen" v-text="getStatus(ItemData)+'/'+ItemData.items.length"></span></div></div>
      <div class="list-body" @click="">
        <div class="img-box">
          <div class="top" >
            <i class="iconfont icon-yifu general" v-if="ItemData.id.indexOf('A03') != -1"></i>
            <i class="iconfont icon-bag_icon general" v-if="ItemData.id.indexOf('A10') != -1"></i>
            <i class="iconfont icon-shafa general" v-if="ItemData.id.indexOf('A13') != -1"></i>
          </div>
        </div>
        <div class="message-box">
          <div class="title">
            <p>预约时间：</p>
            <p>用户姓名：</p>
            <p>收衣地址：</p>
          </div>
          <div class="data">
            <p>{{ItemData.createtime|formatDate}}</p>
            <p>{{ItemData.name}}</p>
            <p>{{ItemData.address}}</p>
          </div>
        </div>
        <div class="fenge"></div>
        <div class="phone-box">
          <img class="img" :src="Dial"/>
        </div>
      </div>
    </div>
    <div style="clear:both"></div>
    <bodys v-bind:ItemData="ItemData" v-if="ItemData!=''"></bodys>
    <div style="clear:both"></div>
    <button class="button" @click="toHangUp">确定上挂</button>
  </div>
</template>
<script>
  import HeadBar from "../Common/HeadBar.vue"
  import SearchBar from "../Common/SearchBar.vue"
  import OrderList from "../InBound/OrderList.vue"
  import Bodys from "./HangUpsBody.vue"
  import {formatDate} from '../../common/js/data'
  import ImgData from '../../json/img.json'
  import SrcData from "../../json/src.json"
  export default {
    name:"HangUps",
    components:{
      HeadBar,
      SearchBar,
      OrderList,
      Bodys
    },
    data(){
      return{
        WhereFrom:'',
        ItemData:'',
        Dial:''
      }
    },
    filters: {
      formatDate(time) {
        var date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm');
      }
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.WhereFrom==''){
          vm.WhereFrom = to.params.from;
          vm.ItemData = to.params.OrderData;
          vm.Dial=ImgData.LinkerImg.Dial.src;
          console.log(vm.ItemData)
        }
      })
    },
    methods: {
      getStatus(data){
        let HangUp = data.items;
        let num = 0;
        HangUp.forEach((item,index)=>{
          if(Number(item.status)==2){
          }else {
            num += 1;
            console.log(2)
          }
        })
        return num
      },
      toHangUp(){
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.HangUps.http;
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{
            orderid:this.ItemData.id,
          }
        }).then(res=>{
          console.log(res)
          this.$router.push({name:'HangUps'})
        })
          .catch(res=>{
            console.log(res)
          })
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .box{
    background: $color-background-big;
    .list{
      width: 100%;
      margin-top: px2rem(80);
      text-align: center;
      .list-bar{
        width: 100%;
        height:px2rem(63);
        background: $color-background-general;
        .payment{
          margin-left: px2rem(241);
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
    .button{
      position: absolute;
      bottom: px2rem(3);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
