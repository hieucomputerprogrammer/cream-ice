import { TestBed } from '@angular/core/testing';

import { OrderDetailsDataService } from './order-details-data.service';

describe('OrderDetailsDataService', () => {
  let service: OrderDetailsDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrderDetailsDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
