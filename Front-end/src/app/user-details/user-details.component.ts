import { User } from './../services/models/user.model';
import { Role } from './../services/models/role.model';
import { RoleDataService } from './../services/data/role-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDataService } from './../services/data/user-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  id: number;
  user: User;
  fileToUpload: File = null;

  constructor(
    private userDataService: UserDataService,
    private route: ActivatedRoute,
    private router: Router,
    public roleDataService: RoleDataService
  ) { }

  ngOnInit(): void {
    this.retrieveAllRoles();
    this.id = this.route.snapshot.params['id'];
    this.user = new User();
    this.userDataService.retrieveUser(this.id).subscribe(data => {
      this.user = data;
      console.log(data);
    });
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

  saveUser() {
    this.userDataService.updateUser(this.id, this.user)
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
      console.log(data);
    });
  }

}
