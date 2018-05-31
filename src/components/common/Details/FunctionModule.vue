<template>
  <div class="fuction-box">
    <div class="Site-box" v-if="From=='InBound'">
      <div class="one-box" @click="toAddClothes">
        <div class="box">
          <div class="top"><i class="iconfont icon-xinjiantianjiabiaodanyemian img"></i></div>
        </div>
        <p>添加衣物</p>
      </div>
      <div class="two-box" @click="toAddDetails">
        <div class="box">
          <div class="top"><i class="iconfont icon-weibiaoti1 imgcom"></i></div>
        </div>
        <p>添加详情</p>
      </div>
    </div>
    <div class="HangUp-box" v-if="From=='HangUp'">
      <div class="one-box" @click="toList">
        <div class="box">
          <div class="top"><i class="iconfont icon-liebiaolist29 imglist"></i></div>
        </div>
        <p>查看物品列表</p>
      </div>
      <div class="two-box">
        <div class="box">
          <div class="top"><i class="iconfont icon-xiaopiaodayin imgtick"></i></div>
        </div>
        <p>查看收单小票</p>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        From:'',
        Items:'',
        SelectData:'',
      }
    },
    created(){
      this.From=this.WhereFrom;
      this.Items=this.OrderData.items;
    },
    props:{
      OrderData:{
        type:Object,
        required:true
      },
      WhereFrom:{
        type:String,
        required:true
      },
      FinishSelect:{
        type:Array,
        required:true
      }
    },
    methods:{
      toAddClothes(){
        this.$router.push({ name: 'AddClothes', params: { OrderName:this.orderNumber,from:this.From }});
      },
      toAddDetails(){
        this.$router.push({name:'ClothesList',params: { Items:this.Items,from:this.From,SelectData:this.FinishSelect }})
      },
      toList(){
        this.$router.push({name:'ClothesList',params: { OrderName:this.orderNumber,from:this.From }})
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  @font-face {
    font-family: MRBlod;
    src: url('../../../../static/font/MRBlod.ttf');
  }
  .fuction-box{
    margin-top: px2rem(41);
    p{
      margin-top: px2rem(24);
      @include font(2);
      font-family: MRBlod;
      color: $color-background-general;
    }
    .one-box{
      float: left;
      margin-left: px2rem(59);
      width: px2rem(246);
      text-align: center;
    }
    .two-box{
      float: left;
      margin-left: px2rem(140);
      width: px2rem(246);
      text-align: center;
    }
    .box{
      width: 100%;
      height: px2rem(234);
      border-radius: 20%;
      background: $color-background-general;
      text-align: center;
      .top{
        position: relative;
        top: 50%;
        transform: translateY(-50%);
        .img{
          color: white;
          @include font(85)
        }
      }
    }
    .imgcom{
      color: white;
      @include font(65)
    }
    .imglist{
      color: white;
      @include font(45)
    }
    .imgtick{
      color: white;
      @include font(75)
    }
    .Site-box{
      overflow: hidden;
    }
    .HangUp-box{
      overflow: hidden;
    }

  }
</style>
