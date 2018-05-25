<template>
  <div class="foot-box">
    <button class="span" @click="toNext" v-if="IsShow">下一步</button>
    <button class="span" @click="toNext" v-if="!IsShow">完成添加</button>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        Select:[],
        IsShow:'',
      }
    },
    props:{
      SelectData:{
        type:Array,
        required:true
      }
    },
    created(){
    },
    watch:{
      '$route'(){
        this.selectPage();
      }
    },
    methods: {
      toNext(){
        this.Select = this.SelectData;
        this.$router.push({name: 'AddChildren', params: { SelectData:this.Select,from:'Site' }});
      },
      selectPage(){
        if(this.$route.name == 'AddClothes'){
          this.IsShow = true;
        }else if(this.$route.name == 'AddChildren'){
          this.IsShow = false;
        }
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .foot-box{
    position: absolute;
    bottom: px2rem(20);
    width: 100%;
    .span{
      @include font(5);
      color: white;
      width: 100%;
      height:px2rem(90);
      background: $color-background-general;
    }
  }
</style>
