<template>
  <div class="add-details-box">
    <head-bar></head-bar>
    <div class="body">
      <search-bar v-on:BarCode="getBarCode" v-bind:barCode="BarCode" v-if="IsShow" v-bind:WhereFrom="WhereFrom"></search-bar>
      <clothes-details  v-on:Before="getBeforeData"  v-bind:BeforeData="BeforeData" v-if="IsShow" v-bind:AfterData="AfterData" v-on:After="getAfterData"></clothes-details>
      <details-image  v-on:SrcData="getSrcData" v-bind:OrderId="OrderId" v-bind:IsShow="IsShow"  v-on:Remarks="getRemarks" v-bind:srcdata="srcdata" v-bind:remark="remark" v-if="remark!=null"></details-image>
      <div>
        <div class="height "></div>
        <div class="operate ">
          <button class="btn-submit" @click="okToDetails">完成编辑</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import HeadBar from '../../Common/HeadBar.vue'
  import SearchBar from '../../Common/SearchBar.vue'
  import ClothesDetails from './ClothesDetails.vue'
  import DetailsImage from './DetailsImage.vue'
  import FootBar from '../../Common/FootBar.vue'
  import SrcData from  "../../../json/src.json"
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
        IsShow:'',
        Arr:[],
        BeforeData:[],
        AfterData:[],
        remark:null,
        srcdata:[],
        isshow:false
      }
    },
    created(){

    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.ClothesItem==''){
          vm.ClothesItem = to.params.Item;
          vm.OrderId = to.params.OrderId;
          vm.Arr = to.params.Arr;
          console.log( vm.Arr);
          if(vm.OrderId.indexOf("A03")==-1){
            vm.IsShow = false;
            if(vm.Arr!=''){
              vm.remark = '';
              vm.Arr.forEach((item,index)=>{
                if(item.id == vm.ClothesItem.id){
                  vm.BarCode = '';
                  vm.srcdata = [];
                  let resultimg=item.image.split(",");
                  let resultPro=item.problemImage.split(",");
                  if(resultimg!=''){
                    for(let i=0;i<resultimg.length;i++){
                      vm.srcdata.push({
                        src:resultimg[i],
                        pro:false
                      });
                    }
                  }
                  if(resultPro!=''){
                    for(let i=0;i<resultPro.length;i++){
                      vm.srcdata.push({
                        src:resultPro[i],
                        pro:true
                      });
                    }
                  }
                  vm.SrcData = vm.srcdata;
                }
              })
            }else {
              vm.remark = '';
            }
          }else {
            if(vm.Arr!=''){
              vm.remark = '';
              let Flag=false;
              vm.Arr.forEach((item,index)=>{
                if(item.id == vm.ClothesItem.id){
                  if(vm.OrderId.indexOf('A03')!=-1){
                    vm.BarCode = '';
                    vm.BarCode = item.barCode;
                  }else {
                    vm.BarCode = ''
                  }
                  vm.srcdata = [];
                  let resultBefore=item.flaw.split(",");
                  let resultAfter=item.washingEffect.split(",");
                  let resultimg=item.image.split(",");
                  let resultPro=item.problemImage.split(",");
                  for(let i=0;i<resultBefore.length;i++){
                    vm.BeforeData.push(resultBefore[i]);
                  }
                  for(let i=0;i<resultAfter.length;i++){
                    vm.AfterData.push(resultAfter[i]);
                  }
                  if(resultimg!=''){
                    for(let i=0;i<resultimg.length;i++){
                      vm.srcdata.push({
                        src:resultimg[i],
                        pro:false
                      });
                    }
                  }
                  if(resultPro!=''){
                    for(let i=0;i<resultPro.length;i++){
                      vm.srcdata.push({
                        src:resultPro[i],
                        pro:true
                      });
                    }
                  }
                  vm.SrcData = vm.srcdata;
                  vm.Before = item.flaw;
                  vm.After = item.washingEffect;
                  vm.remark = item.remark;
                  vm.Remark = item.remark;
                  Flag = true
                }else if(Flag == false){
                  vm.BarCode = String(Number(vm.Arr[vm.Arr.length-1].barCode)+1);
                }
              })
            }else {
              vm.remark = '';
            }
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
        if(this.BarCode!=''||this.BarCode.length==7||this.OrderId.indexOf("A03")==-1){
          let obj = {};
          let img = '';
          let problemImage = '';
          if(this.SrcData!=''){
            this.SrcData.forEach((item,index)=>{
              if(item.pro==false){
                img = img+item.src+','
              }else if(item.pro==true){
                problemImage = problemImage+item.src+','
              }
            });
          }else {
            this.$alert('请先拍照')
          }
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
          this.$alert('确定完成编辑？',['确定','取消']).then(()=>{
            this.$router.push({name:'ClothesList',params:{Obj:obj}})
          })
        }else {
          this.$msg.setShow('条形码不能为空或异常')
        }
      },
     /* toVilateCode(){
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.distinctbarcode.http;
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{barcode:this.BarCode},
        }).then(res=>{
        if(res.data.code == '20017'){
            this.$msg.setShow('存在重复条形码')
        }else if(res.data.code == '0'){
          this.okToDetails()
        }
        })
      }*/
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .add-details-box{
    background: $color-background-big;
    height: 100%;
    padding: 0;
    margin: 0;
    .body{
      height:100%;
      display: flex;
      display:-ms-flex;
      display:-webkit-flex;
      flex-direction: column;

      .button1{
        color: white;
        position: absolute;
        bottom: px2rem(3);
        width: 100%;
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
      .height{
        height: px2rem(90);
      }
      .operate {
        width: 100%;
        position:fixed;
        bottom: px2rem(0);
      }
      .operate .btn-submit {
        width: 100%;
        height: px2rem(90);
        @include font(5);
        line-height: 1;
        color: white;
        border: none;
        padding: 0;
        letter-spacing: 1px;
        border: 0;
        background: $color-background-general;
      }
    }
  }
  .add-details-box:after{content:'';display:table;clear:both;}
</style>
