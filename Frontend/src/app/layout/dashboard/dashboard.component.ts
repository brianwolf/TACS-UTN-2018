import { Component } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  animations: [routerTransition()]
})
export class DashboardComponent {

  public sliders: Array<any> = [];

  constructor() {

    this.sliders.push(
      {
        imagePath: 'assets/images/1.jpg',
        label: 'Buscando Libertad?',
        text: 'Las criptomonedas le permiten realizar transacciones en cualquier parte y horario.'
      },
      {
        imagePath: 'assets/images/2.jpg',
        label: 'Conozca los Beneficios!',
        text: 'Tamaño de emisión fija, valor creciente, liquidez...'
      },
      {
        imagePath: 'assets/images/3.jpg',
        label: 'Bitcoin',
        text: 'El futuro del dinero ya está aqui.'
      }
    );
  }

}
