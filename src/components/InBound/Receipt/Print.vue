<template>
  <div @click.stop @touchmove.prevent class="confirms">
    <div class="confirms-container zh-center" >
      <div class="content">
        <span class="msg">确认是否打印？</span>
      </div>
      <div class="button-box">
        <span class="button" @click="print">打印</span>
        <span class="button" @click="cancels">取消</span>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return {
        Remarks:false,
        Msg:'点击此处输入备注',
        Remark:''
      }
    },
    props:{
      Items:{
        type:Object,
        required:true
      },
      clothesList:{
        type:Array
      }
    },
    methods: {
      print(){
        let Items = this.Items;
        let clothesList = this.clothesList;
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
               clothesGrade:'',
               clothesPrice:(item.laundryProduct.price/100).toFixed(2),
               clothesDefect:defect,
               clothesWashingEffect:washingEffect,
               clothesColor:''
             })
           }
         })
        });
        printObj.orderNumber = Items.number;
        printObj.clothingQuantity = Items.items.length;
        printObj.clothingDate = this.getNowData();
        printObj.takingClothingDate = this.getNowData();
        printObj.customerName=Items.name;
        printObj.customerPhone = Items.phone;
        printObj.customerAddress = Items.address;
        printObj.totalPrice = (Items.amount/100).toFixed(2);
        printObj.servicePrice = '';
        printObj.storeAddress=this.$token.store.area;
        printObj.storePhone=this.$token.store.phone;
        printObj.clerk=this.$token.store.people;
        printObj.storeNumber=this.$token.store.number;
        printObj.clothes = clothes;
//TODO 打印输出
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
      cancels(){
        this.$emit('Status','cancels');
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";

  $color-line: #EAEAEB;
  .confirms{
    z-index: 9998;
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: $color-background-cover;
    .confirms-container {
      @include px2rem(width, 563);
      @include px2rem(height, 174);
      background: #fff;
      border: px2rem(2) solid $color-line;
      @include px2rem(border-radius, 15);
      .content {
        display: flex;
        justify-items: center;

        margin-top: px2rem(30);
        width: 100%;
        @include px2rem(height, 61);
        @include font(8);
        box-sizing: border-box;
        border-bottom: px2rem(4) solid $color-line;
        .msg{
          margin-left: px2rem(30);
        }
      }
      .button-box {
        display: flex;
        .button {
          display: flex;
          justify-content: center;
          align-items: center;
          flex-grow: 1;
          @include px2rem(height, 83);
          @include font(3);
          &:nth-child(1) {
            color: $color-theme;
          }
          &:nth-child(2) {
            color: $color-theme;
            box-sizing: border-box;
            border-left: px2rem(4) solid $color-line;
          }
          &:nth-child(3) {
            box-sizing: border-box;
            border-left: px2rem(4) solid $color-line;
          }
        }
      }
    }
  }
</style>
