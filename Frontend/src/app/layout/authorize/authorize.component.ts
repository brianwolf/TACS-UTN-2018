import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
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

  constructor(private adminService: AdminService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getDeposits();
  }

  getDeposits() {
    this.adminService.getDeposits()
      .subscribe(
        (data: any) => {
          this.deposits.data = data;
          this.initTableFunctions();
          this.proccesing = false;
        }
      );
  }

  approve(number: string) {
    this.proccesing = true;
    this.adminService.approveDeposit(number).subscribe(
      data => this.snackBar.open('Deposito Aprobado.', 'v'),
      error => this.snackBar.open('ERROR: No se pudo realizar la operación.', 'v', { panelClass: 'alert-danger' }),
      () => this.getDeposits()
    );
  }

  reject(number: string) {
    this.proccesing = true;
    this.adminService.rejectDeposit(number).subscribe(
      data => this.snackBar.open('Deposito Rechazado.', 'v', { panelClass: 'alert-warning' }),
      error => this.snackBar.open('ERROR: No se pudo realizar la operación.', 'v', { panelClass: 'alert-danger' }),
      () => this.getDeposits()
    );
  }

  applyFilter(filterValue: string) {
    this.deposits.filter = filterValue.trim().toLowerCase();
  }

  initTableFunctions() {
    this.deposits.sort = this.sort;
    this.deposits.paginator = this.paginator;
  }

}
