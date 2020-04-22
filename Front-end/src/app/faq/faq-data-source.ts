import { FAQ } from './../services/models/faq.model';
import { catchError } from 'rxjs/operators';
import { FaqDataService } from './../services/data/faq-data.service';
import { BehaviorSubject, of, Observable } from 'rxjs';
import { DataSource, CollectionViewer } from '@angular/cdk/collections';

export class FAQDataSource extends DataSource<any> {
    private customersSubject = new BehaviorSubject<FAQ[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private faqService: FaqDataService) {
        super();
    }

    loadFAQs(page: number, size: number) {
        const faqSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.faqService.retrieveAllFAQs(faqSearchCriteria)
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

    connect(collectionViewer: CollectionViewer): Observable<FAQ[]> {
        console.log('Connecting data source.');
        return this.customersSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.customersSubject.complete();
        this.countSubject.complete();
    }
}
