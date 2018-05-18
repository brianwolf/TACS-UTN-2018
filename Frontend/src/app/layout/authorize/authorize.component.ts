import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  animations: [routerTransition()]
})
export class AuthorizeComponent implements OnInit {

  constructor(private adminService: AdminService) { }

  ngOnInit() { }

}
