import { Recipe } from './../services/models/recipe.model';
import { RecipeDataService } from './../services/data/recipe-data.service';
import { CollectionViewer } from '@angular/cdk/collections';
import { DataSource } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { map, catchError, finalize } from 'rxjs/operators';

export class RecipeDataSource extends DataSource<any> {
    private recipesSubject = new BehaviorSubject<Recipe[]>([]);

    private countSubject = new BehaviorSubject<number>(0);

    public counter$ = this.countSubject.asObservable();

    constructor(private recipeService: RecipeDataService) {
        super();
    }

    loadRecipes(page: number, size: number) {
        const recipeSearchCriteria = { page, size };
        this.recipeService.retrieveAllRecipes(recipeSearchCriteria)
        .pipe(
            catchError(() => of([]))
        )
        .subscribe((result: any) => {
            this.recipesSubject.next(result.content);
            this.countSubject.next(result.totalElements);
            }
        );
    }

    connect(collectionViewer: CollectionViewer): Observable<Recipe[]> {
        console.log('Connecting data source.');
        return this.recipesSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.recipesSubject.complete();
        this.countSubject.complete();
    }
}
