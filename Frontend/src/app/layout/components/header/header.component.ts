import { Component } from '@angular/core';
import { ComponentService } from '../../../shared/services/component.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styles: [`i { margin-right: 8px }`]
})
export class HeaderComponent {

  constructor(public cs: ComponentService) { }

}
