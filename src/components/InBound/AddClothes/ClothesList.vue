<template>
  <div class="clothesL-box">
    <div class="title-box" v-for="type in TypeName" @click="select(type)">
      <div class="name-box" >
        <span>{{type}}</span>
        <p><i class="iconfont icon-quanxianfuzhi flipx "  v-show="Type == type"></i></p>
      </div>
    </div>
    <div style="clear:both "></div>
    <div class="item-box">
      <div class="item-name">
        <div v-for="Name in ItemName">
          <P>{{Name}}</P>
        </div>
      </div>
      <div class="type-box">
        <div v-for="item in Items"  >
          <P>{{item}}</P>
        </div>
      </div>
    </div>
  </div>
  <!--<div class="" >
    <div class="" >
      <div v-for="Name in ItemName">
        <P>{{Name}}</P>
      </div>
    </div>
    <div class="" >
      <div v-for="item in Items"  >
        <P>{{item}}</P>
      </div>
    </div>
  </div>-->
</template>
<script>
  import ClothesData from "../../../json/clothes.json"
  export default {
    name:"ClothesList",
    data(){
      return{
        ClotheData:'',
        TypeName:[],
        Type:'',
        ItemName:[],
        Items:[]
      }
    },
    created(){
      this.getClothes();
    },
    methods:{
      getClothes(){
        this.ClotheData = ClothesData;
        for (var tmp in this.ClotheData){
          this.TypeName.push(tmp)
        }
      },
      select(item){
        this.ItemName = [];
        this.Items = [];
        this.Type=item;
        for (var tmp in this.ClotheData[item]){
          this.ItemName.push(tmp);
          this.Items.push(this.ClotheData[item][tmp])
        }
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .clothesL-box{
    .title-box{
      margin-top: px2rem(45);
      margin-left: px2rem(-29);
      .name-box{
        float: left;
        margin-left: px2rem(55);
        text-align: center;
        span{
          float: left;
          @include font(2)
        }
        .flipx{
          @include font(-3);
          color: $color-background-general;
        }
      }
    }
    .item-box{
      margin-left: px2rem(25);
      p{
        @include font(2);
        margin-top: px2rem(55);
      }
      .item-name{
        float: left;
      }
      .type-box{
        float: right;
        margin-right: px2rem(25);
      }
    }
  }
</style>
