import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderdetailsDetailsComponent } from './orderdetails-details.component';

describe('OrderdetailsDetailsComponent', () => {
  let component: OrderdetailsDetailsComponent;
  let fixture: ComponentFixture<OrderdetailsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderdetailsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderdetailsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
