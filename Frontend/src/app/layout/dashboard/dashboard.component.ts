import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  animations: [routerTransition()]
})
export class DashboardComponent implements OnInit {

  public sliders: Array<any> = [];

  constructor() {

    this.sliders.push(
      {
        imagePath: 'assets/images/slider1.jpg',
        label: 'Buscando libertad?',
        text: 'Las criptomonedas le permiten realizar transacciones en cualquier parte y horario.'
      },
      {
        imagePath: 'assets/images/slider2.jpg',
        label: 'Conozca los beneficios!',
        text: 'Tamaño de emisión fija, valor creciente, liquidez...'
      },
      {
        imagePath: 'assets/images/slider3.jpg',
        label: 'Bitcoin',
        text: 'El futuro del dinero ya está aqui.'
      }
    );
  }

  ngOnInit() { }

}
