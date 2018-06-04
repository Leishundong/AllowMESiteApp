<template>
  <div class="add-details-box">
    <head-bar></head-bar>
    <div class="mall-remark">
      <p>衣物备注：</p>
      <input class="note" :placeholder="Message" v-model="Remarks"/>
    </div>
    <details-images v-on:SrcData="getSrcData"></details-images>
    <div style="clear: both" ></div>
    <button class="button" @click="finishOrder">完成编辑</button>
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
        MallData:''
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
        console.log(this.SrcData)
      },
      finishOrder(){
        let mallImg = '';
        this.SrcData.forEach((item,index)=>{
          mallImg = mallImg+item.imgSrc+','
        });
        console.log(this.MallData);
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.MallOrder.http;
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{ orderid:this.MallData.id,waybillimage:mallImg.substring(0,mallImg.length - 1)},
        }).then(res=>{
          console.log(res)
        }).catch(res=>{
          console.log(res)
        })
      }
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
      margin-top: px2rem(365);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
