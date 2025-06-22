import { TestBed } from '@angular/core/testing';

import { ParticiparService } from './participar.service';

describe('ParticiparService', () => {
  let service: ParticiparService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticiparService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
