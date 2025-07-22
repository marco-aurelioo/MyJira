import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticiparProjetosComponent } from './participar-projetos.component';

describe('ProjetosComponent', () => {
  let component: ParticiparProjetosComponent;
  let fixture: ComponentFixture<ParticiparProjetosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticiparProjetosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticiparProjetosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
