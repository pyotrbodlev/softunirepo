import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CauseFullInfoComponent } from './cause-full-info.component';

describe('CauseFullInfoComponent', () => {
  let component: CauseFullInfoComponent;
  let fixture: ComponentFixture<CauseFullInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CauseFullInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CauseFullInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
