import { Payment } from './../services/models/payment.model';
import { PaymentDataService } from './../services/data/payment-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map, catchError, finalize } from 'rxjs/operators';

export class PaymentDataSource extends DataSource<any> {
    private paymentsSubject = new BehaviorSubject<Payment[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private paymentService: PaymentDataService) {
        super();
    }

    loadPayments(page: number, size: number) {
        const paymentSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.paymentService.retrieveAllPayments(paymentSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.paymentsSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    connect(collectionViewer: CollectionViewer): Observable<Payment[]> {
        console.log('Connecting data source.');
        return this.paymentsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.paymentsSubject.complete();
        this.countSubject.complete();
    }
}
