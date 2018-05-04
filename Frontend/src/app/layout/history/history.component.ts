import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  animations: [routerTransition()]
})
export class HistoryComponent implements OnInit {

  today: any;
  threeDays: any;
  lastweek: any;
  lastMonth: any;
  startTimes: any;

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getToday().subscribe(data => this.today = data, error => this.today = 999);
    this.adminService.getThreeDays().subscribe(data => this.threeDays = data, error => this.threeDays = 999);
    this.adminService.getLastweek().subscribe(data => this.lastweek = data, error => this.lastweek = 999);
    this.adminService.getLastMonth().subscribe(data => this.lastMonth = data, error => this.lastMonth = 999);
    this.adminService.getStartTimes().subscribe(data => this.startTimes = data, error => this.startTimes = 999);
  }

}
