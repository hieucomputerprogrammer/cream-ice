import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderdetailsCreateComponent } from './orderdetails-create.component';

describe('OrderdetailsCreateComponent', () => {
  let component: OrderdetailsCreateComponent;
  let fixture: ComponentFixture<OrderdetailsCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderdetailsCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderdetailsCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
