import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'toLocalDate'
})
export class ToLocalDatePipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    return new Date(value).toDateString();
  }

}
