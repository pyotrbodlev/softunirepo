import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewCauseComponent } from './create-new-cause.component';

describe('CreateNewCauseComponent', () => {
  let component: CreateNewCauseComponent;
  let fixture: ComponentFixture<CreateNewCauseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewCauseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewCauseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
