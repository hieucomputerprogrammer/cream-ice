import { TestBed } from '@angular/core/testing';

import { HttpInterceptorBasicAutService } from './http-interceptor-basic-aut.service';

describe('HttpInterceptorBasicAutService', () => {
  let service: HttpInterceptorBasicAutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptorBasicAutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
