import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuadroSprintComponent } from './quadro-sprint.component';

describe('QuadroSprintComponent', () => {
  let component: QuadroSprintComponent;
  let fixture: ComponentFixture<QuadroSprintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuadroSprintComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuadroSprintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
