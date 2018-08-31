export default {
  token:'',
  accountId:'',
  store:'',
  getToken(NewToken){
    this.token = NewToken;
  },
  getAccountId(NewAccountId){
    this.accountId = NewAccountId
  },
  getStore(NewStore){
    this.store = NewStore
  }
}
