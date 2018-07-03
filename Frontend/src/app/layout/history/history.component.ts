import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styles: [` td { text-align: center } `],
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
    const errorMsg = 'No hay datos';
    this.adminService.getToday().subscribe(data => this.today = data, error => this.today = errorMsg);
    this.adminService.getThreeDays().subscribe(data => this.threeDays = data, error => this.threeDays = errorMsg);
    this.adminService.getLastweek().subscribe(data => this.lastweek = data, error => this.lastweek = errorMsg);
    this.adminService.getLastMonth().subscribe(data => this.lastMonth = data, error => this.lastMonth = errorMsg);
    this.adminService.getStartTimes().subscribe(data => this.startTimes = data, error => this.startTimes = errorMsg);
  }

}
