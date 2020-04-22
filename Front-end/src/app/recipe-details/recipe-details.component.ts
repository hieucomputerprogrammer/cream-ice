import { Recipe } from './../services/models/recipe.model';
import { catchError, map } from 'rxjs/operators';
import { IceCreamDataService } from './../services/data/ice-cream-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeDataService } from './../services/data/recipe-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {
  id: number;
  recipe: Recipe;
  iceCreams: any[];
  readonly maxSize = 2000000000;

  constructor(
    private recipeDataService: RecipeDataService,
    private route: ActivatedRoute,
    private router: Router,
    private iceCreamDataService: IceCreamDataService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.recipe = new Recipe();
    this.recipeDataService.retrieveRecipe(this.id).subscribe(data => this.recipe = data);
    const iceCreamSearchCriteria = {
      page: 0,
      size: this.maxSize
    };
    this.iceCreamDataService.retrieveAllIceCreams(iceCreamSearchCriteria)
      .pipe(map(response => response.content))
      .subscribe(data => this.iceCreams = data);
  }

  handleFileInput(files: FileList) {
    const file = files.item(0);
    console.log(file);
    const reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = () => {
      this.recipe.imageInBase64 = reader.result as string;
    };
    reader.onerror = function() {
        console.log('there are some problems');
    };
  }

  saveRecipe() {
    this.recipeDataService.updateRecipe(this.id, this.recipe)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['recipes']);
        }
      );
  }

}
