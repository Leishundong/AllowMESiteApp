<template>
  <div class="box">
    <div class="bar-box">
      <p>瑕疵：</p>
      <div style="background: white;text-align: center">
        <select class="select" v-model="Selected">
          <option class="option" v-for="option in options">
            {{ option.type }}
          </option>
        </select>
      </div>
      <i class="iconfont icon-arrow_down_full img"></i>
      <span class="button"><div class="span"><span>添加</span></div></span>
    </div>
    <div style="clear: both"></div>
    <div class="bar-box">
      <p>洗后效果：</p>
      <div style="background: white;text-align: center">
        <select class="select" v-model="SelectedTwo">
          <option class="option" v-for="option in options">
            {{ option.type }}
          </option>
        </select>
      </div>
      <i class="iconfont icon-arrow_down_full img"></i>
      <span class="button"><div class="span"><span>添加</span></div></span>
    </div>
    <div style="clear: both"></div>
    <div class="bar-box">
      <p>衣物备注：</p>
      <input class="note" :placeholder="Message"/>
    </div>
    <div style="clear: both"></div>
  </div>
</template>
<script>
  export default {
    name:'ClothesDetails',
    data(){
      return{
        Message:'备注信息',
        Selected:'请选择',
        SelectedTwo:'请选择',
        options: [
          { type:'脏了1'},
          { type:'脏了2'},
          { type:'脏了3'},
          { type:'脏了4'},
          { type:'脏了5'},
          { type:'脏了6'}
        ]
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .box{
    margin-left: px2rem(36);
    p{
      @include font(5);
      color: $color-background-general;
    }
    input{
      @include font(5);
      float: left;
      margin-top: px2rem(22);
      text-indent: px2rem(12);
      width: px2rem(520);
      line-height: px2rem(73);
    }
    .select{
      @include font(5);
      float: left;
      margin-top: px2rem(22);
      text-indent: px2rem(12);
      width: px2rem(520);
      height: px2rem(73);
      border: solid px2rem(-13) #000;
      color: black;
      appearance:none;
      .option{
        color: black;
        text-align:center;
        @include font(-1);
        background: white;
      }

    }
    .note{
      width: px2rem(688);
    }
    .bar-box{
      margin-top: px2rem(47);
      height: px2rem(73);
      .img{
        position: absolute;
        @include font(5);
        color: $color-background-general;
        margin-left: px2rem(-50);
        margin-top: 5%;
      }
    }
    .button{
      margin-top: px2rem(22);
      float: left;
      text-align: center;
      width: px2rem(165);
      height: px2rem(73);
      background: $color-background-general;
      color: white;
      @include font(5);
      .span{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
      }
    }
  }
</style>
