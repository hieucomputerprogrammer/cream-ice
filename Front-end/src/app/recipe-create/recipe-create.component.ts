import { Recipe } from './../services/models/recipe.model';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeDataService } from './../services/data/recipe-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-create',
  templateUrl: './recipe-create.component.html',
  styleUrls: ['./recipe-create.component.css']
})
export class RecipeCreateComponent implements OnInit {
  id: number;
  recipe: Recipe;

  constructor(
    private recipeDataService: RecipeDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.recipe = new Recipe();
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

  addRecipe() {
    this.recipeDataService.createRecipe(this.recipe)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['recipes']);
      }
    );
  }

}
