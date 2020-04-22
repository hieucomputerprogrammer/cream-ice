import { Role } from './../services/models/role.model';
import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { UserDataSource } from './user-data-source';
import { Router } from '@angular/router';
import { UserDataService } from './../services/data/user-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'name',
    'gender',
    'address',
    'dateOfBirth',
    'phoneNumber',
    'email',
    'avatar',
    'status',
    'update',
    'delete'
  ];
  userDataSource: UserDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private userDataService: UserDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.userDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.userDataSource.loadUsers(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    this.userDataSource = new UserDataSource(this.userDataService);
    this.userDataSource.loadUsers(0, 5);
  }

  refreshUsers() {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.userDataSource.loadUsers(0, 5);
  }

  deleteUser(id) {
    this.userDataService.deleteUser(id).subscribe(
      response => {
        console.log(response);
        this.message = `User with ID number ${id} successfully deleted!`;
        this.refreshUsers();
      }
    );
  }

  updateUser(id) {
    console.log(`Updated User with ID number ${id}.`);
    this.router.navigate(['users', id]);
  }

  addUser() {
    this.router.navigate(['create-user']);
  }

}
