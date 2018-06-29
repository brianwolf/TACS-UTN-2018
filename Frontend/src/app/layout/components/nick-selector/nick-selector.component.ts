import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { AdminService } from '../../../shared/services/admin.service';
import { AlertService } from '../../../shared/services/alert.service';

@Component({
  selector: 'app-nick-selector',
  templateUrl: './nick-selector.component.html',
  styles: ['mat-form-field { width: 100% }']
})
export class NickSelectorComponent implements OnInit {

  nicks: string[] = [];
  nicksControl: FormControl = new FormControl(null, this.validateNick());
  filteredNicks: Observable<string[]>;

  constructor(public alertService: AlertService, public adminService: AdminService) { }

  ngOnInit() {
    this.fillNicks();
  }

  fillNicks() {
    this.adminService.getUsers().subscribe(
      (data: any) => this.nicks = data,
      error => this.alertService.error('No se pudo cargar a los usuarios.'),
      () => this.filteredNicks = this.nicksControl.valueChanges.pipe(startWith(''), map(val => this.filterUser(val)))
    );
  }

  filterUser(val: string): string[] {
    return this.nicks.filter(nick => nick.toLowerCase().startsWith(val.toLowerCase()));
  }

  validateNick() {
    return (control: FormControl) => {
      return this.nicks.includes(control.value) ? null : { 'validateNick': { valid: false } };
    };
  }

}
