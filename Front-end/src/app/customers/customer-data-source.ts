import { Customer } from './../services/models/customer.model';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { CustomerDataService } from '../services/data/customer-data.service';
import { map, catchError, finalize } from 'rxjs/operators';

export class CustomerDataSource extends DataSource<any> {
    private customersSubject = new BehaviorSubject<Customer[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private customerService: CustomerDataService) {
        super();
    }

    loadCustomers(page: number, size: number) {
        const customerSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.customerService.retrieveAllCustomers(customerSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.customersSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    // connect(): Observable<Customer[]> {
    //     let customerSearchCriteria = { page: 1, size: 2 };
    //     return this.customerService.retrieveAllCustomers(customerSearchCriteria)
    //         .pipe(map(response => response.content));
    // }

    connect(collectionViewer: CollectionViewer): Observable<Customer[]> {
        console.log('Connecting data source.');
        return this.customersSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.customersSubject.complete();
        this.countSubject.complete();
    }
}
