import { Recipe } from './../models/recipe.model';
import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RecipeDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllRecipes(recipeSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/recipes`, { params: recipeSearchCriteria });
  }

  retrieveRecipesByIceCreamId(iceCreamId): Observable<any> {
    const searchParam = {
      iceCreamId
    };
    return this.httpClient.get(`${API_URL}/filter-recipes`, { params: searchParam });
  }

  deleteRecipe(id) {
    return this.httpClient.delete(`${API_URL}/recipes/${id}`);
  }

  retrieveRecipe(id) {
    return this.httpClient.get<Recipe>(`${API_URL}/recipes/${id}`);
  }

  updateRecipe(id, recipe) {
    return this.httpClient.put(`${API_URL}/recipes/${id}`, recipe);
  }

  createRecipe(recipe) {
    return this.httpClient.post(`${API_URL}/recipes/`, recipe);
  }
}
