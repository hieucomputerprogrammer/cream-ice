import { AngularMaterialModule } from './angular-material/angular-material.module';
import { HttpInterceptorBasicAuthService } from './services/http/http-interceptor-basic-aut.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductsListComponent } from './products-list/products-list.component';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { UsersComponent } from './users/users.component';
import { RecipesComponent } from './recipes/recipes.component';
import { OrdersComponent } from './orders/orders.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { FeedbacksComponent } from './feedbacks/feedbacks.component';
import { LogoutComponent } from './logout/logout.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { IceCreamComponent } from './ice-cream/ice-cream.component';
import { CustomersComponent } from './customers/customers.component';
import { FaqComponent } from './faq/faq.component';
import { PaymentComponent } from './payment/payment.component';
import { BannerComponent } from './banner/banner.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { FaqDetailsComponent } from './faq-details/faq-details.component';
import { FeedbackDetailsComponent } from './feedback-details/feedback-details.component';
import { IceCreamDetailsComponent } from './ice-cream-details/ice-cream-details.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { PaymentDetailsComponent } from './payment-details/payment-details.component';
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';
import { CustomerCreateComponent } from './customer-create/customer-create.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserCreateComponent } from './user-create/user-create.component';
import { IceCreamCreateComponent } from './ice-cream-create/ice-cream-create.component';
import { RecipeCreateComponent } from './recipe-create/recipe-create.component';
import { FeedbackCreateComponent } from './feedback-create/feedback-create.component';
import { FaqCreateComponent } from './faq-create/faq-create.component';
import { OrderCreateComponent } from './order-create/order-create.component';
import { PaymentCreateComponent } from './payment-create/payment-create.component';
import { OrderdetailsComponent } from './orderdetails/orderdetails.component';
import { OrderdetailsDetailsComponent } from './orderdetails-details/orderdetails-details.component';
import { OrderdetailsCreateComponent } from './orderdetails-create/orderdetails-create.component';
import { CartComponent } from './cart/cart.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsListComponent,
    LoginComponent,
    ErrorComponent,
    UsersComponent,
    RecipesComponent,
    OrdersComponent,
    MenuComponent,
    FooterComponent,
    FeedbacksComponent,
    LogoutComponent,
    IceCreamComponent,
    CustomersComponent,
    FaqComponent,
    PaymentComponent,
    BannerComponent,
    UserDetailsComponent,
    CustomerDetailsComponent,
    FaqDetailsComponent,
    FeedbackDetailsComponent,
    IceCreamDetailsComponent,
    OrderDetailsComponent,
    PaymentDetailsComponent,
    RecipeDetailsComponent,
    CustomerCreateComponent,
    UserCreateComponent,
    IceCreamCreateComponent,
    RecipeCreateComponent,
    FeedbackCreateComponent,
    FaqCreateComponent,
    OrderCreateComponent,
    PaymentCreateComponent,
    OrderdetailsComponent,
    OrderdetailsDetailsComponent,
    OrderdetailsCreateComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AngularMaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorBasicAuthService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
