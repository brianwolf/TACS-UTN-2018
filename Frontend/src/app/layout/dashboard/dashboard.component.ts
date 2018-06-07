import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  animations: [routerTransition()]
})
export class DashboardComponent implements OnInit {

  public sliders: Array<any> = [];

  constructor() {

    this.sliders.push(
      {
        imagePath: 'https://cdn.pixabay.com/photo/2018/05/17/21/26/cryptocurrency-3409725_1280.jpg',
        label: 'Buscando libertad?',
        text: 'Las criptomonedas le permiten realizar transacciones en cualquier parte y horario.'
      },
      {
        imagePath: 'https://cdn.pixabay.com/photo/2017/01/25/12/31/bitcoin-2007769_1280.jpg',
        label: 'Conozca los beneficios!',
        text: 'Tamaño de emisión fija, valor creciente, liquidez...'
      },
      {
        imagePath: 'https://cdn.pixabay.com/photo/2018/05/17/20/56/cryptocurrency-3409655_1280.jpg',
        label: 'Bitcoin',
        text: 'El futuro del dinero ya está aqui.'
      }
    );
  }

  ngOnInit() { }

}
