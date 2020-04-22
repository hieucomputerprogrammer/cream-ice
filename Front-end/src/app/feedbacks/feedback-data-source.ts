import { Feedback } from './../services/models/feedback.model';
import { FeedbackDataService } from './../services/data/feedback-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { CustomerDataService } from '../services/data/customer-data.service';
import { map, catchError, finalize } from 'rxjs/operators';

export class FeedbackDataSource extends DataSource<any> {
    private feedbacksSubject = new BehaviorSubject<Feedback[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private feedbackService: FeedbackDataService) {
        super();
    }

    loadFeedbacks(page: number, size: number) {
        const feedbackSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.feedbackService.retrieveAllFeedbacks(feedbackSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.feedbacksSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    // connect(): Observable<Customer[]> {
    //     let customerSearchCriteria = { page: 1, size: 2 };
    //     return this.customerService.retrieveAllCustomers(customerSearchCriteria)
    //         .pipe(map(response => response.content));
    // }

    connect(collectionViewer: CollectionViewer): Observable<Feedback[]> {
        console.log('Connecting data source.');
        return this.feedbacksSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.feedbacksSubject.complete();
        this.countSubject.complete();
    }
}
