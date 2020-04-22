import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';

const AngularMaterialComponents = [
  MatButtonModule,
  MatPaginatorModule,
  MatTableModule
];

@NgModule({
  imports: [AngularMaterialComponents],
  exports: [AngularMaterialComponents]
})
export class AngularMaterialModule { }
