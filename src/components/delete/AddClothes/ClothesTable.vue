<template>
  <div class="clothesL-box">
    <div class="title-box" v-for="type in TypeName" @click="select(type)" :ref="type">
      <div class="name-box" >
        <span>{{type}}</span>
        <p><i class="iconfont icon-quanxianfuzhi flipx "  v-show="Type == type"></i></p>
      </div>
    </div>
    <div style="clear:both "></div>
    <div class="item-box">
      <div v-for="(items,index) in Items" class="items-box" @click="selectIndex(items,index)" >
        <div :class="(TypeSelect.type==items.type&&TypeSelect.index==index)?'select ':''">
          <span>{{items.name}}</span> <span  class="right">{{items.item}}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import ClothesData from "../../../json/clothes.json"
  export default {
    name:"ClothesList",
    data(){
      return{
        ClotheData:'',
        TypeName:[],
        Items:[],
        Type:'',
        TypeSelect:{},
        SelectData:[],
      }
    },
    created(){
      this.getClothes();
    },
    mounted(){
      this.initialClicks();
    },
    methods:{
      getClothes(){
        this.ClotheData = ClothesData;
        for (var tmp in this.ClotheData){
          this.TypeName.push(tmp)
        }
      },
      select(item){
        this.Items = [];
        this.Type=item;
        for (var tmp in this.ClotheData[item]){
          this.Items.push({
            type:item,
            name:tmp,
            item:this.ClotheData[item][tmp]
          })
        }
      },
      selectIndex(items,index){
        this.TypeSelect={
          type:items.type,
          index:index
        };
        let isTrue = true;
        if(this.SelectData!=''){
          for(var i=0;i<this.SelectData.length;i++){
            if(this.SelectData[i]==items){
              isTrue = false;
              return
            }
          };
          if(isTrue==true){
            this.SelectData.push(items);
          }else {
            return
          }
        }else {
          this.SelectData.push(items);
        };
        this.$emit('Select-Data',this.SelectData);
      },
      initialClicks(){
        this.$refs[this.TypeName[0]][0].click();
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
      .items-box{
        margin-top: px2rem(15);
        span{
          line-height:px2rem(70);
          margin-left: px2rem(25);
          @include font(2);
        }
        .right{
          float: right;
          margin-right: px2rem(48);
        }
      }
      .select {
        background: #cccccc;
      }
    }
  }
</style>
