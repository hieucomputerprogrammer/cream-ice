import { CartComponent } from './cart/cart.component';
import { PaymentCreateComponent } from './payment-create/payment-create.component';
import { OrderCreateComponent } from './order-create/order-create.component';
import { FaqCreateComponent } from './faq-create/faq-create.component';
import { FeedbackCreateComponent } from './feedback-create/feedback-create.component';
import { RecipeCreateComponent } from './recipe-create/recipe-create.component';
import { IceCreamCreateComponent } from './ice-cream-create/ice-cream-create.component';
import { UserCreateComponent } from './user-create/user-create.component';
import { CustomerCreateComponent } from './customer-create/customer-create.component';
import { PaymentDetailsComponent } from './payment-details/payment-details.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { FaqDetailsComponent } from './faq-details/faq-details.component';
import { FeedbackDetailsComponent } from './feedback-details/feedback-details.component';
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { PaymentComponent } from './payment/payment.component';
import { FaqComponent } from './faq/faq.component';
import { CustomersComponent } from './customers/customers.component';
import { IceCreamComponent } from './ice-cream/ice-cream.component';
import { RouteGuardService } from './services/route-guard.service';
import { LogoutComponent } from './logout/logout.component';
import { FeedbacksComponent } from './feedbacks/feedbacks.component';
import { OrdersComponent } from './orders/orders.component';
import { UsersComponent } from './users/users.component';
import { RecipesComponent } from './recipes/recipes.component';
import { ErrorComponent } from './error/error.component';
import { ProductsListComponent } from './products-list/products-list.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IceCreamDetailsComponent } from './ice-cream-details/ice-cream-details.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  // { path: 'welcome/:username', component: WelcomeComponent, canActivate: [RouteGuardService]},
  { path: 'products-list', component: ProductsListComponent },
  { path: 'users', component: UsersComponent, canActivate: [RouteGuardService] },
  { path: 'users/:id', component: UserDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-user', component: UserCreateComponent, canActivate: [RouteGuardService] },
  { path: 'customers', component: CustomersComponent, canActivate: [RouteGuardService] },
  { path: 'customers/:id', component: CustomerDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-customer', component: CustomerCreateComponent, canActivate: [RouteGuardService] },
  { path: 'ice-creams', component: IceCreamComponent, canActivate: [RouteGuardService] },
  { path: 'ice-creams/:id', component: IceCreamDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-ice-cream', component: IceCreamCreateComponent, canActivate: [RouteGuardService] },
  { path: 'recipes', component: RecipesComponent, canActivate: [RouteGuardService] },
  { path: 'recipes/:id', component: RecipeDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-recipe', component: RecipeCreateComponent, canActivate: [RouteGuardService] },
  { path: 'feedbacks', component: FeedbacksComponent, canActivate: [RouteGuardService] },
  { path: 'feedbacks/:id', component: FeedbackDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-feedback', component: FeedbackCreateComponent, canActivate: [RouteGuardService] },
  { path: 'faqs', component: FaqComponent, canActivate: [RouteGuardService] },
  { path: 'faqs/:id', component: FaqDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-faq', component: FaqCreateComponent, canActivate: [RouteGuardService] },
  { path: 'orders', component: OrdersComponent, canActivate: [RouteGuardService] },
  { path: 'orders/:id', component: OrderDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-order', component: OrderCreateComponent, canActivate: [RouteGuardService] },
  { path: 'payments', component: PaymentComponent, canActivate: [RouteGuardService] },
  { path: 'payments/:id', component: PaymentDetailsComponent, canActivate: [RouteGuardService] },
  { path: 'create-payment', component: PaymentCreateComponent, canActivate: [RouteGuardService] },
  { path: 'cart', component: CartComponent, canActivate: [RouteGuardService] },
  { path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService] },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
