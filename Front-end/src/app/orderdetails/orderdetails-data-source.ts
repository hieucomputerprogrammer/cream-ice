import { OrderDetails, OrderDetailsDataService } from './../services/data/order-details-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map, catchError, finalize } from 'rxjs/operators';

export class OrderDetailsDataSource extends DataSource<any> {
    private orderDetailsSubject = new BehaviorSubject<OrderDetails[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private orderDetailsService: OrderDetailsDataService) {
        super();
    }

    loadOrderDetails(page: number, size: number) {
        const orderDetailsSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.orderDetailsService.retrieveAllOrderDetails(orderDetailsSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.orderDetailsSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    connect(collectionViewer: CollectionViewer): Observable<OrderDetails[]> {
        console.log("Connecting data source.");
        return this.orderDetailsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.orderDetailsSubject.complete();
        this.countSubject.complete();
    }
}
