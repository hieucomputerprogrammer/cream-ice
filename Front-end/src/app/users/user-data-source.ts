import { User } from './../services/models/user.model';
import { UserDataService } from './../services/data/user-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map, catchError, finalize } from 'rxjs/operators';

export class UserDataSource extends DataSource<any> {
    private usersSubject = new BehaviorSubject<User[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private userService: UserDataService) {
        super();
    }

    loadUsers(page: number, size: number) {
        const userSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.userService.retrieveAllUsers(userSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.usersSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    // connect(): Observable<Customer[]> {
    //     let customerSearchCriteria = { page: 1, size: 2 };
    //     return this.customerService.retrieveAllCustomers(customerSearchCriteria)
    //         .pipe(map(response => response.content));
    // }

    connect(collectionViewer: CollectionViewer): Observable<User[]> {
        console.log('Connecting data source.');
        return this.usersSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.usersSubject.complete();
        this.countSubject.complete();
    }
}
