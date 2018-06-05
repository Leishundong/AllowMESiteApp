<template>
  <div class="add-details-box">
    <head-bar></head-bar>
    <search-bar v-on:BarCode="getBarCode" v-if="IsShow" v-bind:WhereFrom="WhereFrom"></search-bar>
    <clothes-details v-on:Before="getBeforeData" v-on:After="getAfterData"  v-if="IsShow"></clothes-details>
    <details-image v-on:SrcData="getSrcData" v-on:Remarks="getRemarks"></details-image>
    <div style="clear: both" ></div>
    <button class="button" @click="okToDetails"v-if="IsShow">完成编辑</button>
    <button class="button1" @click="okToDetails"v-if="!IsShow">完成编辑</button>
  </div>
</template>
<script>
  import HeadBar from '../../Common/HeadBar.vue'
  import SearchBar from '../../Common/SearchBar.vue'
  import ClothesDetails from './ClothesDetails.vue'
  import DetailsImage from './DetailsImage.vue'
  import FootBar from '../../Common/FootBar.vue'
  export default {
    name:'AddDetails',
    components:{
      HeadBar,
      SearchBar,
      ClothesDetails,
      DetailsImage,
      FootBar
    },
    data(){
      return{
        ClothesItem:'',
        WhereFrom:'测试',
        BarCode:'',
        Before:'',
        After:'',
        SrcData:'',
        Remark:'',
        OrderId:'',
        IsShow:''
      }
    },
    created(){
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.ClothesItem==''){
          vm.ClothesItem = to.params.Item;
          vm.OrderId = to.params.OrderId;
          console.log(vm.WhereFrom);
          if(vm.OrderId.indexOf("A03")==-1){
            vm.IsShow = false;
          }else {
            vm.IsShow = true;
          }
        }
      })
    },
    methods:{
      getBarCode(data){
        this.BarCode = data;
      },
      getBeforeData(data){
        this.Before = data;
      },
      getAfterData(data){
        this.After = data;
      },
      getSrcData(data){
        this.SrcData = data;
      },
      getRemarks(data){
        this.Remark = data;
      },
      okToDetails(){
        let obj = {};
        let img = '';
        let problemImage = '';
        this.SrcData.forEach((item,index)=>{
          if(item.pro==false){
            img = img+item.imgSrc+','
          }else if(item.pro==true){
            problemImage = problemImage+item.imgSrc+','
          }
        });
        if(this.OrderId.indexOf("A03")==-1){
            obj={
              id:this.ClothesItem.id,
              image:img.substring(0,img.length - 1),
              problemImage: problemImage.substring(0,problemImage.length - 1)
            };
        }else {
          obj={
            id:this.ClothesItem.id,
            barCode:this.BarCode,
            flaw:this.Before,
            washingEffect:this.After,
            remark:this.Remark,
            image:img.substring(0,img.length - 1),
            problemImage: problemImage.substring(0,problemImage.length - 1)
          };
        }
        this.$router.push({name:'ClothesList',params:{Obj:obj}})
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
    .button{
      position: absolute;
      bottom: px2rem(-530);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
    .button1{
      position: absolute;
      bottom: px2rem(3);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
