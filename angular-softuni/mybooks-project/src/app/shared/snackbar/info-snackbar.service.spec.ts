import { TestBed } from '@angular/core/testing';

import { InfoSnackbarService } from './info-snackbar.service';

describe('InfoSnackbarService', () => {
  let service: InfoSnackbarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InfoSnackbarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
