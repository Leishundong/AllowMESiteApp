<template>
  <div class="list-box">
    <head-bar></head-bar>
    <div class="message">
      <p>衣物条形码<span>{{ItemData.barCode}}</span></p>
      <p>类型<span v-text="returenType()"></span></p>
      <p>洗后效果<span>{{ItemData.washingEffect}}</span></p>
      <p>瑕疵<span>{{ItemData.flaw}}</span></p>
      <p>衣物备注<span>{{ItemData.remark}}</span></p>
    </div>
    <button class="button" @click="hangUp">衣物上挂</button>
  </div>
</template>
<script>
  import HeadBar from "./HeadBar.vue"
  import SrcData from "../../json/src.json"
  export default {
    name:'HangUpClothes',
    components:{
      HeadBar
    },
    data(){
      return{
        Where:'',
        ItemData:'',
        OrderID:'',
        Type:'',
      }
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        vm.ItemData = to.params.HItem;
        vm.OrderID = to.params.OrderId;
        console.log(vm.ItemData)
      })
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
    created(){
      this.returenType()
    },
    activated(){
      this.returenType()
    },
    methods:{
      toAddDetails(){
        this.$router.push({name:'AddDetails'})
      },
      toHangUpClothes(){
        this.$router.push({name:'HangUpClothes'})
      },
      getType(type){
        let typ = '';
        let types = Number(type);
        if(types==0){
          typ='上衣类';
          return typ
        }else if(types==1){
          typ='裤裙类';
          return typ
        }else if(types==2){
          typ='皮草类';
          return typ
        }else if(types==3){
          typ='装饰类';
          return typ
        }else if(types==4){
          typ='鞋包类';
          return typ
        }
      },
      getTypes(type){
        let name = '';
        let types = Number(type);
        if(types==1){
          name='洗衣';
          return name
        }else if(types==2){
          name='高端洗护';
          return name
        }
      },
      returenType(){
        if(this.OrderID.indexOf("A03")==-1){
          this.Show = false
        }else {
          let type = this.getType(this.ItemData.laundryProduct.category);
          let types = this.getTypes(this.ItemData.laundryProduct.type);
          return types+type
        }
      },
      hangUp(){
       this.$alert("是否确定上挂") .then(_=>{
         let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.HangUp.http;
         this.$ajax({
           method:'post',
           url: src,
           params:{
             orderid:this.OrderID,
             orderitemid:ItemData.id
           }
         })
       }).then(_=>{
         this.$msg("上挂成功")
         this.$router.push({name:'HangUpList'})
       })
         .catch(_=>{
           this.$msg("上挂失败")
         })
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .list-box{
    background: $color-background-big;
    height: 100%;
    .message{
      margin-left: px2rem(35);
      p{
        margin-top: px2rem(30);
        @include font(2);
        color:$color-background-general;
        span{
          float: right;
          margin-right: px2rem(400);
        }
      }
    }
    .button{
      color: white;
      position: absolute;
      bottom: px2rem(1);
      width: 100%;
      height: px2rem(90);
      background: $color-background-general;
      @include font(5);
    }
  }
</style>
