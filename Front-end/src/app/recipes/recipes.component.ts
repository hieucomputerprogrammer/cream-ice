import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { RecipeDataSource } from './recipe-data-source';
import { Router } from '@angular/router';
import { RecipeDataService } from './../services/data/recipe-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'title',
    'description',
    'details',
    'image',
    'price',
    'status',
    'createdAt',
    'viewCount',
    'update',
    'delete'
  ];
  recipeDataSource: RecipeDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private recipeDataService: RecipeDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.recipeDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    this.paginator.page
    .pipe(
        tap(() => this.recipeDataSource.loadRecipes(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    this.recipeDataSource = new RecipeDataSource(this.recipeDataService);
    this.recipeDataSource.loadRecipes(0, 5);
  }

  refreshRecipes() {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.recipeDataSource.loadRecipes(0, 5);
  }

  deleteRecipe(id) {
    this.recipeDataService.deleteRecipe(id).subscribe(
      response => {
        console.log(response);
        this.message = `Recipe with ID number ${id} successfully deleted!`;
        this.refreshRecipes();
      }
    );
  }

  updateRecipe(id) {
    console.log(`Update Recipe with ID number ${id}.`);
    this.router.navigate(['recipes', id]);
  }

  addRecipe() {
    this.router.navigate(['create-recipe']);
  }

}
