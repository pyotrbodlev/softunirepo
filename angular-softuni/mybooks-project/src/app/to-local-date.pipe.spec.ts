import { ToLocalDatePipe } from './to-local-date.pipe';

describe('ToLocalDatePipe', () => {
  it('create an instance', () => {
    const pipe = new ToLocalDatePipe();
    expect(pipe).toBeTruthy();
  });
});
