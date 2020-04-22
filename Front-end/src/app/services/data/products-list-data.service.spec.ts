import { TestBed } from '@angular/core/testing';

import { ProductsListDataService } from './products-list-data.service';

describe('ProductsListDataService', () => {
  let service: ProductsListDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductsListDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
