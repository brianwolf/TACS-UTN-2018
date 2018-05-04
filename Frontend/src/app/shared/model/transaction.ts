export class Transaction {

  coin: string;
  actual: number;
  buy: number;
  dif: number;

  constructor(coin: string, actual: number, buy: number, dif: number) {
    this.coin = coin;
    this.actual = actual;
    this.buy = buy;
    this.dif = dif;
  }

}
