<template>
  <div class="add-details-box">
    <head-bar></head-bar>
    <details-images
      v-on:SrcData="getSrcData"   v-bind:OrderId="OrderId"   v-bind:srcdata="srcdata" v-bind:remark="remark" >
    </details-images>
    <div style="clear: both" ></div>
    <button class="button" @click="clickFinishOrder">完成编辑</button>
  </div>
</template>
<script>
  import HeadBar from '../../Common/HeadBar.vue'
  import DetailsImages from '../AddDetails/DetailsImage.vue'
  import SrcData from  "../../../json/src.json"
  export default {
    name:'AddDetails',
    components:{
      HeadBar,
      DetailsImages,
    },
    data(){
      return{
        SrcData:[],
        Remarks:'',
        Message:'备注信息',
        MallData:'',
        remark:'',
        OrderId:'',
        srcdata:[]
      }
    },
    created(){
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        vm.MallData = to.params.OrderData;
        console.log(vm.MallData);
      })
    },
    methods: {
      getSrcData(data){
        this.SrcData = data;
      },
      finishOrder(){
        let mallImg = '';
        this.SrcData.forEach((item)=>{
          mallImg = mallImg+item.src+','
        });
        let mallImgs = mallImg.substring(0,mallImg.length - 1);
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.MallOrder.http;
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{ orderid:this.MallData.id,waybillimage:mallImgs},
        }).then(res=>{
          if(res.data.msg == '成功'){
            this.$msg.setShow('上传成功');
           this.$router.push({name:'Inbound'})
          }else if(res.data.msg==null){
            this.$msg.setShow('数据异常，请重试');
          }else {
            this.$msg.setShow(res.data.msg);
          }
        }).catch(res=>{
          this.$alert(res.response.data.msg).then(()=>{
            if(res.response.data.code == '1'){
              this.$router.push({name:'Login'})
            }
          });
        })
      },
      clickFinishOrder(){
        this.$alert("完成将上传图片",['确定','否']).then(()=>{
          this.finishOrder()
        })
      },
      getRemarks(data){
        this.Remark = data;
      },
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .add-details-box{
    background: $color-background-big;
    height: 100%;
    .mall-remark{
      margin-left: px2rem(36);
      margin-top: px2rem(50);
      p{
        @include font(5);
        color: $color-background-general;
      }
      input{
        @include font(5);
        float: left;
        margin-top: px2rem(22);
        text-indent: px2rem(12);
        width: px2rem(660);
        line-height: px2rem(73);
      }
    }


    .button{
      color: white;
      position: absolute;
      bottom: px2rem(0);
      border: none;
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
