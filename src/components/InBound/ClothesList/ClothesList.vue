<template>
  <div class="clothes-list-box">
    <head-bar></head-bar>
    <div class="body">
      <list v-bind:Items = "Items" v-bind:Remark="Remark" v-bind:SelectData="SelectData"
            v-bind:OrderId="OrderId" v-if="Items!=''" v-bind:OrderData="OrderData" v-bind:Arr="Arr"></list>
      <remark v-if="showConfirm" v-on:Remarks="getRemarks" v-on:Remark="getRemark" v-bind:OrderId="OrderId"></remark>
      <div>
        <div class="height"></div>
        <div class="operate ">
          <button class="edit" @click="clickeRemark">添加备注</button>
          <span class="line"></span>
          <button class="write" @click="toReceipt">打印</button>
          <span class="line"></span>
          <button class="inBound" @click="clikeInBound">确认入站</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import HeadBar from "../../Common/HeadBar.vue"
  import FootBar from "../../Common/FootBar.vue"
  import List from "./List.vue"
  import SrcData from  "../../../json/src.json"
  import Remark from "../../Common/Remark.vue"
  export default {
    name:'ClothesList',
    components:{
      HeadBar,
      FootBar,
      List,
      Remark
    },
    data(){
      return{
        WhereFrom:'',
        SelectData:['测试'],
        Items:'',
        laundryOrderItemList:[],
        OrderId:'',
        Img:[],
        showConfirm:false,
        Remark:'无',
        Arr:[],
        OrderData:''
      }
    },
    beforeRouteEnter (to, from, next) {
      next(vm => {
        if(from.name=='Details'){
          vm.WhereFrom = '';
          vm.Items = '';
          vm.OrderData = '';
          vm.WhereFrom = to.params.from;
          vm.Items = to.params.Items.items;
          console.log(vm.Items);
          vm.OrderData = to.params.Items;
          vm.OrderId = to.params.OrderId;
        }
        if(from.name=='Receipt'){
          vm.WhereFrom = 'InBound'
        }
        if(from.name == 'AddDetails'){
          let Obj = to.params.Obj;
          vm.WhereFrom = 'InBound';
          if(Obj!=''){
            let flag = true;
            let number = '';
            if(vm.OrderId.indexOf("A03")!=-1){
              if(vm.laundryOrderItemList!=''){
                vm.laundryOrderItemList.forEach((item,index)=>{
                  if(Obj.id == item.id){
                    flag = false;
                    number = index;
                  }
                });
                if(flag==true){
                  vm.laundryOrderItemList.push(Obj);
                }else {
                  vm.laundryOrderItemList[number] = Obj;
                }

              }else {
                vm.laundryOrderItemList.push(Obj);
              }
              vm.Arr = vm.laundryOrderItemList;
            }else {
              if(vm.Img!=''){
                vm.Img.forEach((item,index)=>{
                  if(Obj.id == item.id){
                    flag = false;
                    number = index;
                  }
                });
                if(flag==true){
                  vm.Img.push(Obj);
                }else {
                  vm.Img[number]=Obj;
                }
              }else {
                vm.Img.push(Obj);
              }
              vm.Arr = vm.Img;
            }
          }
        }
      })
    },
    beforeRouteLeave(to,from,next){
        this.WhereFrom = '';
        if(to.name == 'Details'||to.name == 'InBound'){
          this.OrderData = '';
          this.Items = '';
        }else if(to.name == 'InBound'){

        }
        next();
    },
    methods:{
      inBound(){
        let src;
        let obj;
        if(this.OrderId.indexOf("A03")==-1){
          obj = this.Img;
          src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.FurnitureOrder.http;
        }else {
          src = SrcData.LinkerSrc.AtAll.Http+SrcData.LinkerSrc.InBound.http;
          obj = this.laundryOrderItemList;
        }
        this.$ajax({
          method:'post',
          url: src,
          headers: {'x-auth-token': this.$token.token},
          params:{ orderid:this.OrderId},
          data: obj
        }).then(res=>{
          if(res.data.msg=='成功'){
            this.laundryOrderItemList = [];
            this.Img = [];
            this.Items = '';
            this.OrderData= '';
            this.Arr = [];
            this.$msg.setShow('入站成功');
            this.$router.push({name:'Inbound'});
          }else if(res.data.msg==null){
            this.$msg.setShow('数据异常，请重试')
          }
        }).catch(res=>{
          console.log('ssss',res);
          this.$alert(res.response.data.msg).then(()=>{
            if(res.response.data.code == '1'){
              this.$router.push({name:'Login'})
            }
          });
        })
      },
      toReceipt(){
        this.$router.push({name: 'Receipt',params:{Items:this.OrderData,From:this.WhereFrom,laundryOrderItemList:this.laundryOrderItemList,remark:this.Remark}});
      },
      clikeInBound(){
        let flag = true;
        let msg = '';
        if(this.laundryOrderItemList.length!=this.OrderData.items.length){
          flag = false;
          msg = '入站数据不完全'
        }
        if(flag){
          if(this.laundryOrderItemList){
            this.laundryOrderItemList.forEach(item=>{
              if(item.barCode==''){
                flag = false;
                msg = '条形码为空'
              }
            })
          }
        }
        if(this.OrderId.indexOf("A03")==-1){
          flag = true
        }
        if(flag){
          this.$alert("是否确定入站",['是','否']).then(()=>{
           this.inBound()
          })
        }else {
          this.$msg.setShow(msg);
        }

      },
      getRemarks(data){
        this.showConfirm=data
      },
      getRemark(data){
        this.Remark = data
      },
      clickeRemark(){
        this.showConfirm=true
      }
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  @import "~common/css/mixin";
  @import "~common/css/variable";
  .clothes-list-box{
    background: $color-background-big;
    min-height: 100%;
    padding: 0;
    margin: 0;
    .body{
      min-height:100%;
      display: flex;
      display:-ms-flex;
      display:-webkit-flex;
      flex-direction: column;
      .height{
        height: px2rem(90);
      }
      .operate {
        position:fixed;
        bottom: px2rem(0);
        .line{
          height: px2rem(90);
          color: white;
          width: px2rem(1);
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
          width: px2rem(246);
          height: px2rem(90);
          background: $color-background-general;
          @include font(5);
          border: none;
        }
      }
    }
  }
</style>
