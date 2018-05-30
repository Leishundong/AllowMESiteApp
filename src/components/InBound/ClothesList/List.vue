<template>
  <div class="list-box">
    <div class="message">
      <p>总数<span v-text="Total"></span></p>
      <p>总价<span v-text="Num"></span></p>
      <p>物流备注<span>aaa</span></p>
      <p>入站备注<span>aaa</span></p>
    </div>
    <div class="list"  v-for="n in SelectDatas" @click="toAddDetails" v-if="WhereFrom == 'InBound'">
      <div class="img-box">
        <div class="top"><i class="iconfont icon-yifu img"></i></div>
      </div>
      <span>{{n.name}}</span><span class="right">{{n.item}}</span>
      <span class="tops">{{n.type}}</span>
    </div>
    <div class="list"  v-for="n in parseInt(3)" @click="toHangUpClothes" v-if="WhereFrom == 'HangUp'">
      <div class="img-box">
        <div class="top"><i class="iconfont icon-yifu img"></i></div>
      </div>
      <span>1</span><span class="right">未上挂</span>
      <span class="tops">aaa</span>
    </div>
  </div>
</template>
<script>
  export default {
    name:'List',
    data(){
      return{
        Where:'',
        Num:'',
        Total:'',
        SelectDatas:[],
        Number:'',
      }
    },
    props:{
      WhereFrom:{
        type:String,
        required:true
      },
      SelectData:{
        type:Array,
        required:true
      },
      OrderNumber:{
        type:String,
        required:true
      }
    },
    created(){
      this.Where = this.WhereFrom;
      this.SelectDatas = this.SelectData;
      this.Number = this.OrderNumber;
      this.getData()
    },
    /*computed:{*/
     /* ShowList(){
        if(this.WhereFrom=='HangUp'){
          return true
        }else {
          return false
        }
      },
      Show(){
        if(this.WhereFrom=='Inbound'){
          return true
        }else {
          return false
        }
      }
    },*/
    methods:{
      getData(){
        this.Total = this.SelectDatas.length;
        let num = 0;
        this.SelectData.forEach((item,index)=>{
            num += Number(item.item)
        })
        this.Num = num;
      },
      toAddDetails(){
        this.$router.push({name:'AddDetails'})
      },
      toHangUpClothes(){
        this.$router.push({name:'HangUpClothes'})
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .list-box{
    margin-left: px2rem(17);
    .message{
      p{
        margin-top: px2rem(30);
        @include font(2);
        color: black;
        span{
          float: right;
          margin-right: px2rem(400);
        }
      }
    }
    .list{
      margin-top: px2rem(40);
      height: px2rem(109);
      width: 100%;
      span{
        @include font(1);
        position: absolute;
        margin-left: px2rem(103);
        margin-top: px2rem(23);
      }
      .right{
        margin-left: px2rem(432);
      }
      .tops{
        margin-top: px2rem(65);
      }
      .img-box{
        float: left;
        width: px2rem(109);
        height: 100%;
        background: rgb(23,28,96);
        text-align: center;
        .top{
          position: relative;
          top: 50%;
          transform: translateY(-50%);
          .img{
            color: white;
            @include font(20)
          }
        }
      }
    }
  }
</style>
