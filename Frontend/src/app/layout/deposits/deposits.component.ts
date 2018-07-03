import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-deposits',
  templateUrl: './deposits.component.html',
  styleUrls: ['../layout.component.scss'],
  animations: [routerTransition()]
})
export class DepositsComponent implements OnInit {

  displayedColumns = ['date', 'number', 'amount', 'state'];
  deposits = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getDeposits();
  }

  getDeposits() {
    this.userService.deposits().subscribe(data => this.initTable(data));
  }

  initTable(data) {
    this.deposits.data = data;
    this.deposits.sort = this.sort;
    this.deposits.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.deposits.filter = filterValue.trim().toLowerCase();
  }

}
