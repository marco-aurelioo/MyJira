import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AprovarSolicitacoesComponent } from './aprovar-solicitacoes.component';

describe('AprovarSolicitacoesComponent', () => {
  let component: AprovarSolicitacoesComponent;
  let fixture: ComponentFixture<AprovarSolicitacoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AprovarSolicitacoesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AprovarSolicitacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
