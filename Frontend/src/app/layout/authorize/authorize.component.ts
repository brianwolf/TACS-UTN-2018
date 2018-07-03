import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  styleUrls: ['../layout.component.scss'],
  animations: [routerTransition()]
})
export class AuthorizeComponent implements OnInit {

  displayedColumns = ['date', 'number', 'userNick', 'amount', 'state'];
  deposits = new MatTableDataSource<any>();
  proccesing;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public alertService: AlertService, public adminService: AdminService) { }

  ngOnInit() {
    this.getDeposits();
  }

  getDeposits() {
    this.adminService.getDeposits().subscribe(
      data => this.initTable(data),
      error => this.alertService.error(error.error.message),
      () => this.proccesing = false
    );
  }

  approve(number: string) {
    this.proccesing = true;
    this.adminService.approveDeposit(number).subscribe(
      success => this.alertService.success('Deposito Aprobado.'),
      error => this.alertService.error(error.error.message),
      () => this.getDeposits()
    );
  }

  reject(number: string) {
    this.proccesing = true;
    this.adminService.rejectDeposit(number).subscribe(
      success => this.alertService.warning('Deposito Rechazado.'),
      error => this.alertService.error(error.error.message),
      () => this.getDeposits()
    );
  }

  applyFilter(filterValue: string) {
    this.deposits.filter = filterValue.trim().toLowerCase();
  }

  initTable(data) {
    this.deposits.data = data;
    this.deposits.sort = this.sort;
    this.deposits.paginator = this.paginator;
  }

}
