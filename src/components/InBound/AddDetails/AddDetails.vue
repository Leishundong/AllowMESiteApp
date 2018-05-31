<template>
  <div class="add-details-box">
    <head-bar></head-bar>
    <search-bar></search-bar>
    <clothes-details></clothes-details>
    <details-image></details-image>
  </div>
</template>
<script>
  import HeadBar from '../../Common/HeadBar.vue'
  import SearchBar from '../../Common/SearchBar.vue'
  import ClothesDetails from './ClothesDetails.vue'
  import DetailsImage from './DetailsImage.vue'
  import FootBar from '../../Common/FootBar.vue'
  export default {
    name:'AddDetails',
    components:{
      HeadBar,
      SearchBar,
      ClothesDetails,
      DetailsImage,
      FootBar
    },
    data(){
      return{
        ClothesItem:'',
        WhereFrom:'',
        SelectData:['测试']
      }
    },
    created(){
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if( vm.ClothesItem==''){
          vm.ClothesItem = to.params.Item;
          vm.WhereFrom = to.params.From;
          console.log(vm.ClothesItem);
        }
      })
    },
    methods:{

    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .add-details-box{
    background: $color-background-big;
    height: 100%;
  }
</style>
