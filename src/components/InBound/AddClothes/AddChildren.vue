<template>
  <div class="add-box">
    <head-bar></head-bar>
    <div class="item-box">
      <div v-for="item in SelectData" class="box">
        <span v-text="item.name"></span><span  v-text="item.item"></span><button class="right">删除</button>
      </div>
    </div>
    <foot-bar v-bind:SelectData = "SelectData"></foot-bar>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import FootBar from "../../Common/FootBar.vue"
  export default {
    components:{
      HeadBar,
      FootBar
    },
    data(){
      return{
        SelectData:''
      }
    },
    created(){
      this.getDatas();
    },
    methods:{
      getDatas(){
        this.SelectData=this.$route.params.SelectData;
        console.log(this.SelectData);
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .add-box{
    height: 100%;
    background: $color-background-big;
    .item-box{
      margin-top: px2rem(55);
      margin-left: px2rem(25);
      .box{
        margin-top: px2rem(45);
        span{
          @include font(2);
          color: #000000;
        }
        .right{
          @include font(2);
          text-align: center;
          float: right;
          width: px2rem(104);
          height: px2rem(41);
          background: $color-background-general;
          margin-right: px2rem(35);
        }
      }
    }
  }
</style>
