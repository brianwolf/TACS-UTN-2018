import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  animations: [routerTransition()]
})
export class HistoryComponent implements OnInit {

  today;
  threeDays;
  lastweek;
  lastMonth;
  startTimes;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    const _error = 'No hay datos';
    this.adminService.getToday().subscribe(data => this.today = data, error => this.today = _error);
    this.adminService.getThreeDays().subscribe(data => this.threeDays = data, error => this.threeDays = _error);
    this.adminService.getLastweek().subscribe(data => this.lastweek = data, error => this.lastweek = _error);
    this.adminService.getLastMonth().subscribe(data => this.lastMonth = data, error => this.lastMonth = _error);
    this.adminService.getStartTimes().subscribe(data => this.startTimes = data, error => this.startTimes = _error);
  }

}
