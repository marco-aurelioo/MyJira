import { TestBed } from '@angular/core/testing';

import { ParticiparProjetosService } from './participar-projetos.service';

describe('ParticiparProjetosService', () => {
  let service: ParticiparProjetosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParticiparProjetosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
