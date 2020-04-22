import { Order } from './../services/models/order.model';
import { OrderDataService } from './../services/data/order-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map, catchError, finalize } from 'rxjs/operators';

export class OrderDataSource extends DataSource<any> {
    private ordersSubject = new BehaviorSubject<Order[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private orderService: OrderDataService) {
        super();
    }

    loadOrders(page: number, size: number) {
        const orderSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.orderService.retrieveAllOrders(orderSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.ordersSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    connect(collectionViewer: CollectionViewer): Observable<Order[]> {
        console.log('Connecting data source.');
        return this.ordersSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.ordersSubject.complete();
        this.countSubject.complete();
    }
}
