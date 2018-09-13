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
      receiptData:{
        type:Object
      }
    },
    methods: {
      print(){
        this.$loading.show('请等待...');
        this.$emit('Status','cancels');
        let receiptData = this.receiptData;
        let checkLetterCount=(str)=>{
          if(!str) return 0;
          let count=0;
          for (let codePoint of str) {
            count+=(/[^\u4e00-\u9fa5]/).test(codePoint)?1:2;
          }
          return count;
        };
        let clothesinfo = receiptData.clothes.reduce( (prev, cur)=>{
          let iteminfo = cur.clothesName + "(" + cur.clothesId + ")";
          let priceinfo = "" + (cur.clothesPrice);

          return prev + '\n' + iteminfo + ' '.repeat(32 - checkLetterCount(iteminfo) - priceinfo.length) + priceinfo
            + "\n" + "瑕疵："+" "+cur.clothesDefect + "\n" +"洗后效果："+" "+ cur.washingEffect
        }, '');


        let printstr = `--------------------------------
交易单号:${receiptData.orderNumber}
服务热线:400-0878-315
本店地址:${receiptData.storeAddress}
服务内容:${receiptData.serviceContent}
衣物详情
--------------------------------
名称/衣物条码               价格
${clothesinfo}
--------------------------------
总数:${receiptData.clothingQuantity}件
总价:${receiptData.totalPrice}元
--------------------------------
收单时间:${receiptData.singleTime}
收单人:${receiptData.clerk}
用户姓名:${receiptData.customerName}
收单地址:${receiptData.customerAddress}
联系方式:${receiptData.customerPhone}
--------------------------------
           莫好克温馨提示：
1、凭票取衣，此单遗失请立即到店挂失，以免被他人领取。
2、超过2000元的贵重衣服请选择保值洗涤。
3、衣服送洗请自行掏清口袋东西以免发生纠纷。
4、超过六个月未取取衣物，本店将不通知客户，自行处理。取衣时请当面确认洗涤质量、数量，有异议请当面提出。谢谢合作！


`;
        console.log(printstr);
        alert(printstr);
        ble.print(printstr,
         ()=>{
            this.$loading.hide();
            window.alert("打印成功！");
          },
          (errormsg)=> {
            this.$loading.hide();
            window.alert(errormsg);
          });

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
