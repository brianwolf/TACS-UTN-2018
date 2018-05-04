export class User {

  nick: string;
  pass: string;
  confirmPassword: string;
  totalCoins: number;
  transactions: number;
  todayTransactions: number;
  lastAccess: string;


  constructor(
    nick?,
    totalCoins?,
    transactions?,
    todayTransactions?,
    lastAccess?
  ) {
    this.nick = nick;
    this.totalCoins = totalCoins;
    this.transactions = transactions;
    this.todayTransactions = todayTransactions;
    this.lastAccess = lastAccess;
  }

}
