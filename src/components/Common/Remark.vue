<template>
  <div @click.stop @touchmove.prevent class="confirms">
    <div class="confirms-container zh-center" >
      <div class="input-box">
        <input class="input" :placeholder="Msg" v-model="Remark"/>
      </div>
      <div class="button-box">
        <span class="button" @click="confirm">确定</span>
        <span class="button" @click="cancels">取消</span>
      </div>
    </div>
  </div>
</template>
<script>
import SrcData from "../../json/src.json"
  export default {
    data(){
      return {
        Remarks:false,
        Msg:'点击此处输入备注',
        Remark:''
      }
    },
    props:{
      OrderId:{
        type:String,
        required:true
      }
    },
    methods: {
      confirm(){
        if(this.Remark!=''){
          let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.ModifyRemark.http;
          this.$ajax({
            method:'post',
            url: src,
            headers: {'x-auth-token': this.$token.token},
            params:{ orderid:this.OrderId,remark :this.Remark},
          }).then(res=>{
            this.$msg.setShow('添加成功');
            this.$emit('Remarks',this.Remarks);
            this.$emit('Remark',this.Remark);
            this.Remark = ''
          }).catch(res=>{
            this.$alert(res.response.data.msg).then(()=>{
              if(res.response.data.code == '1'){
                this.$router.push({name:'Login'})
              }
            });
          })
        }else {
          this.$alert('请先输入备注');
        }
      },
      cancels(){
        this.$emit('Remarks',this.Remarks);
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
      @include px2rem(height, 254);
      background: #fff;
      border: px2rem(2) solid $color-line;
      @include px2rem(border-radius, 15);
      .content {
        display: flex;
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
      .input-box{
        height: px2rem(166);
        border-bottom: px2rem(4) solid $color-line;
        .input{
          outline:none;
          text-align: center;
          margin-top: px2rem(40);
          width: 100%;
          @include font(12);
          line-height: px2rem(90);
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
            box-sizing: border-box;
            border-left: px2rem(4) solid $color-line;
          }
        }
      }
    }
  }
</style>
