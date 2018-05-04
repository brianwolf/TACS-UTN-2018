import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  animations: [routerTransition()]
})
export class CompareComponent implements OnInit {

  users: String[];

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getUsers()
      .subscribe(data => this.fillSelectors(data), error => this.users = ['a', 'b', 'c']);
  }

  fillSelectors(data: Object) {
    console.log(data);
    console.log(this.users);
  }

}
