import { User } from './../services/models/user.model';
import { Role } from './../services/models/role.model';
import { RoleDataService } from './../services/data/role-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDataService } from './../services/data/user-data.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {
  id: number;
  user: User;

  constructor(
    private userDataService: UserDataService,
    private route: ActivatedRoute,
    private router: Router,
    public roleDataService: RoleDataService
  ) { }

  ngOnInit(): void {
    this.retrieveAllRoles();
    this.user = new User();
  }

  handleFileInput(files: FileList) {
    const file = files.item(0);
    console.log(file);
    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = () => {
      this.user.avatarInBase64 = reader.result as string;
    };
    reader.onerror = function() {
        console.log('there are some problems');
    };
  }

  addUser(form: NgForm) {
    console.log(form.value.roles)
    form.value.roles = [{'id' : parseInt(form.value.roles)}];
    this.userDataService.createUser(form.value)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['users']);
      }
    );
  }

  retrieveAllRoles() {
    this.roleDataService.retrieveAllRoles().subscribe(data => {
      this.roleDataService.roles = data as Role[];
    });
  }

}
