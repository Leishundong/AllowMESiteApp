<template>
  <div class="foot-box">
    <div style="clear: both"></div>
    <div class="he"></div>
    <div v-if="AddClothes">
      <button class="span" @click="toNext" v-if="IsShow">下一步</button>
      <button class="span" @click="finishAdd" v-if="!IsShow">完成添加</button>
    </div>
    <div v-if="!AddClothes">
      <button class="span" @click="finishEdit" v-if="IsShow">完成编辑</button>
      <div class="clothes-list" v-if="!IsShow">
        <div v-if="TwoShow">
          <button class="edit">添加备注</button><button class="write" @click="toReceipt">打印</button><button class="inBound">确认入站</button>
        </div>
        <div v-if="!TwoShow">
          <button class="receipt">查看备注</button><button class="receipt">打印发票</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        Select:[],
        IsShow:'',
        AddClothes:'',
        TwoShow:'',
      }
    },
    props:{
      SelectData:{
        type:Array,
        required:true
      }
    },
    created(){
      this.selectPage();
    },
    watch:{
      '$route'(){
        this.selectPage();
      }
    },
    methods: {
      toNext(){
        this.Select = this.SelectData;
        this.$router.push({name: 'AddChildren', params: { SelectData:this.Select}});
      },
      selectPage(){
        if(this.$route.name == 'AddClothes'){
          this.IsShow = true;
          this.AddClothes = true
        }else if(this.$route.name == 'AddChildren'){
          this.IsShow = false;
          this.AddClothes = true
        }else if(this.$route.name == 'AddDetails'){
          this.TwoShow = true;
          this.IsShow = true;
          this.AddClothes = false
        }else if(this.$route.name == 'Receipt'){
          this.TwoShow = false;
          this.IsShow = false;
          this.AddClothes = false
        }else if(this.$route.name == 'ClothesList'){
          this.TwoShow = true;
          this.IsShow = false;
          this.AddClothes = false
        }
      },
      finishAdd(){
        this.$router.push({name:'Details'});
      },
      finishEdit(){
        this.$router.push({name: 'ClothesList'});
      },
      toReceipt(){
        this.$router.push({name: 'Receipt'});
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .foot-box{
    position: absolute;
    bottom: px2rem(5);
    width: 100%;
    .clothes-list{
      .receipt{
        color: white;
        float: left;
        width: px2rem(373);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
      .edit{
        color: white;
        float: left;
        width: px2rem(251);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
      .write{
        color: white;
        float: left;
        width: px2rem(253);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
      .inBound{
        color: white;
        float: left;
        width: px2rem(243);
        height: px2rem(90);
        background: $color-background-general;
        @include font(5);
        border: none;
      }
    }
    .span{
      @include font(5);
      color: white;
      width: 100%;
      height:px2rem(90);
      background: $color-background-general;
    }
  }
</style>
