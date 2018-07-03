import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { AdminService } from '../../../shared/services/admin.service';
import { AlertService } from '../../../shared/services/alert.service';

@Component({
  selector: 'app-nick-selector',
  templateUrl: './nick-selector.component.html',
  styles: [`mat-form-field { width: 100% }`]
})
export class NickSelectorComponent implements OnInit {

  nicks: string[] = [];
  filteredNicks: Observable<string[]>;
  nick: FormControl = new FormControl(null, this.validateNick());

  constructor(public alertService: AlertService, public adminService: AdminService) { }

  ngOnInit() {
    this.fillNicks();
  }

  fillNicks() {
    this.adminService.getUsers().subscribe(
      (data: any) => this.nicks = data,
      error => this.alertService.error('No se pudo cargar a los usuarios.'),
      () => this.filteredNicks = this.nick.valueChanges.pipe(startWith(''), map(val => this.filterUser(val)))
    );
  }

  filterUser(val: string): string[] {
    return this.nicks.filter(nick => nick.toLowerCase().startsWith(val.toLowerCase()));
  }

  validateNick() {
    return (nick: FormControl) => {
      return this.nicks.includes(nick.value) ? null : { 'validateNick': { valid: false } };
    };
  }

}
