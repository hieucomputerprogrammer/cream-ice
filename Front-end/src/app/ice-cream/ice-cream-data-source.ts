import { IceCream } from './../services/models/ice-cream.model';
import { IceCreamDataService } from './../services/data/ice-cream-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map, catchError, finalize } from 'rxjs/operators';

export class IceCreamDataSource extends DataSource<any> {
    private iceCreamsSubject = new BehaviorSubject<IceCream[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private iceCreamService: IceCreamDataService) {
        super();
    }

    loadIceCreams(page: number, size: number) {
        const iceCreamSearchCriteria = { page, size };
        // use pipe operator to chain functions with Observable type
        this.iceCreamService.retrieveAllIceCreams(iceCreamSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        // subscribe method to receive Observable type data when it is ready
        .subscribe((result: any) => {
            this.iceCreamsSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    connect(collectionViewer: CollectionViewer): Observable<IceCream[]> {
        console.log('Connecting data source.');
        return this.iceCreamsSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.iceCreamsSubject.complete();
        this.countSubject.complete();
    }
}
