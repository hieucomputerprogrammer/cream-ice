import { CartDataService } from './../services/data/cart-data.service';
import { Recipe } from './../services/models/recipe.model';
import { IceCreamDataSource } from '../ice-cream/ice-cream-data-source';
import { IceCreamDataService } from '../services/data/ice-cream-data.service';
import { RecipeDataService } from '../services/data/recipe-data.service';
import { ProductsListDataService } from '../services/data/products-list-data.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
  welcomeMessage = 'Welcome to CreamIce!';
  iceCream: string;
  iceCreams = [];
  recipes = [];
  username = '';
  selectedIceCreamId: number;

  constructor(
    private route: ActivatedRoute,
    private productsListDataService: ProductsListDataService,
    private recipeDataService: RecipeDataService,
    private iceCreamDataService: IceCreamDataService,
    private cartDataService: CartDataService
  ) { }

  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    const iceCreamSearchCriteria = {
      page: 0, size: 2000000000
    };
    this.iceCreamDataService.retrieveAllIceCreams(iceCreamSearchCriteria).subscribe(response => this.iceCreams = response.content);
    const recipeSearchCriteria = {
      page: 0, size: 2000000000
    };
    this.recipeDataService.retrieveAllRecipes(recipeSearchCriteria).subscribe(response => {
      for (const recipe of response.content) {
          recipe.quantity = 1;
      }
      this.recipes = response.content;
     });
  }

  getIceCreams() {
    this.productsListDataService.getIceCreamsDataFromTheBackend().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => this.handleErrorResponse(error));
  }

  handleSuccessfulResponse(response) {
    this.iceCream = response[0].name;
  }

  handleErrorResponse(error) {
    this.iceCream = error.error.message;
  }

  filterRecipesByIceCreamId(iceCreamId: number) {
    this.selectedIceCreamId = iceCreamId;
    this.recipeDataService.retrieveRecipesByIceCreamId(iceCreamId).subscribe(response => this.recipes = response);
  }

  addToCart(recipe) {
    const params = {
      userId: 2,
      productId: recipe.id,
      quantity: recipe.quantity
    };
    this.cartDataService.addItemToCart(params).subscribe(
      response => window.alert(recipe.quantity + ' ' + recipe.title + '(s) added to cart!'));
  }

}
