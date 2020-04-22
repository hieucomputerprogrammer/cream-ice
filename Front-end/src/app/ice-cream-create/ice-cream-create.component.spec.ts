import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IceCreamCreateComponent } from './ice-cream-create.component';

describe('IceCreamCreateComponent', () => {
  let component: IceCreamCreateComponent;
  let fixture: ComponentFixture<IceCreamCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IceCreamCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IceCreamCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
