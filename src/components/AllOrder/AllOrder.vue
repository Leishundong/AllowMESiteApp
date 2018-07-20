<template>
  <div class="order-list-box">
    <head-bar></head-bar>
    <div class="navigation-bar-box">
      <div class="navigation-bar">
        <div class="navigation-box" v-for="(navigator,index) in navigation" @click="clickNav(navigator.name)">
          <div :class="navigator.color">
            <i :class="navigator.i"></i>
          </div>
          <span class="navigation-name">{{navigator.name}}</span>
          <div class="navigation-line" v-if="navigator.show"></div>
        </div>
      </div>
    </div>
    <div class="order-list-msg" v-if="getShowList()"><span>暂无该状态订单</span></div>
    <order-list class="top" v-bind:OrderLister="OrderLister" v-bind:WhereFrom="WhereFrom" v-if="OrderLister!=''"></order-list>
  </div>
</template>

<script>
  import HeadBar from '../Common/HeadBar.vue'
  import OrderList from '../Common/SearchList.vue'
  import SrcData from "../../json/src.json"
  export default {
    name:'AllOrder',
    components:{
      HeadBar,
      OrderList
    },
    data(){
      return {
        OrderLister:'',
        WhereFrom:'测试',
        navigation:[
          {name:'新订单',i:'iconfont icon-dingdan1',color:'icon-bg',show:false},
          {name:'已入站',i:'iconfont icon-dingdanchulizhong',color:'icon-bg color1',show:false},
          {name:'已上挂',i:'iconfont icon-dingdanyichenggong',color:'icon-bg color2',show:false}
          ]
      }
    },
   /* beforeRouteEnter(to, from, next){
      next(vm=>{
        if(from.name == 'Home'){
          vm.WhereFrom = to.params.from;
          let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
          vm.$ajax({
            method:'post',
            url: src,
            headers: {'x-auth-token': vm.$token.token},
            params:{
              storeid:vm.$token.accountId
            }
          }).then(res=>{
            if(res.data.data!=''&&res.data.data!=null){
              let allOrder = res.data.data;
              for (let v of allOrder){
                if(v.id.indexOf('A03')==-1){
                  if(v.status==4){
                    v.status = 3;
                  }
                }
              }
              allOrder.sort(vm.createComparisonFunction('status'));
              vm.OrderLister = allOrder;
            }else {
              vm.$alert('获取数据异常，请返回重试').then(()=>{
                vm.$router.push({name:'Home'});
              })
            }
          }).catch(res=>{
            vm.$alert(res.response.data.msg).then(()=>{
              if(res.response.data.code == '1'){
                vm.$router.push({name:'Login'})
              }
            });
          });
        }
      })
    },*/
    beforeRouteEnter(to, from, next){
      next(vm=>{
        vm.WhereFrom = to.params.from;
        vm.clickNav('新订单')
      })
    },
    beforeRouteLeave(to,from,next){
      if(to.name == "Home"){
        this.OrderLister=[];
        this.WhereFrom='';
      }
      next();
    },
   /* beforeRouteEnter(to,from,next){
      next(vm=>{
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
        vm.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': vm.$token.token},
          params:{
            storeid:vm.$token.accountId
          }
        }).then(res=>{
          let allOrder = res.data.data;
          for (let v of allOrder){
            if(v.id.indexOf('A03')==-1){
              v.status = 3;
              console.log(v);
            }
          }
          allOrder.sort(vm.createComparisonFunction('status'));
          vm.OrderLister = allOrder;
        }).catch(res=>{
          console.log(res)
        })
      })
    },*/
    methods:{
      createComparisonFunction(propertyName) {//接收属性名
        return function(object1, object2) {
          let value1 = object1[propertyName];
          let value2 = object2[propertyName];
          //制定比较规则
          if (value1 < value2) {
            return -1;
          } else if (value1 > value2) {
            return 1;
          } else {
            return 0;
          }
        }
      },
      clickNav(navigator){
        let type = '';
        this.navigation.forEach((item)=>{
          if(item.name == navigator){
            item.show = true
          }else {
            item.show = false
          }
        });//控制路由条
        if(navigator == '新订单'){
          type=1;
        }else if(navigator == '已入站'){
          type=2;
        }else if(navigator == '已上挂'){
          type=3;
        }
        this.getOrderData(type);
      },
      getOrderData(type){
        let src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.AllOrder.http;
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{
            storeid:this.$token.accountId,
            type:type
          }
        }).then(res=>{
          if(res.data.data!=null){
            let allOrder = res.data.data;
            for (let v of allOrder){
              if(v.id.indexOf('A03')==-1){
                if(v.status==4){
                  v.status = 3;
                }
              }
            }
            this.OrderLister = allOrder;
          }else {
            this.$alert('获取数据异常，请返回重试').then(()=>{
              this.$router.push({name:'Home'});
            })
          }
        }).catch(res=>{
          this.$alert(res.response.data.msg).then(()=>{
            if(res.response.data.code == '1'){
              this.$router.push({name:'Login'})
            }
          });
        });
      },
      getShowList(){
        if(this.OrderLister!=''){
          return false
        }else{
          return true
        }
      }
    }
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
.order-list-box{
  background: $color-background-big;
  min-height: 100%;
  .order-list-msg{
    margin-top: px2rem(504);
    text-align: center;
    span{
      color: #787878;
      @include font(5);
    };
  }
  .navigation-bar-box{
    position: fixed;
    width: 100%;
    background: white;
    z-index: 9999;
    .navigation-bar{
      position: relative;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
      height: px2rem(167);
      .navigation-box{
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 33.333%;
        .icon-bg{
          display: flex;
          justify-content: center;
          align-items: center;
          width: px2rem(72);
          height: px2rem(72);
          border-radius: 50%;
          background: $color-background-general;
          @include font(13);
          color: white;
        }
        .color1{
          background: #0032ef
        }
        .color2{
          background: #8700ef
        }
        .navigation-name{
          margin-top: px2rem(19);
          @include font(-1)
        }
        .navigation-line{
          position: absolute;
          bottom:0;
          height: px2rem(13);
          background: #7fd7f7;
          width: 33.333%;
        }
      }
    }
  }
  .top{
    margin-top: px2rem(171);
  }
}
</style>
