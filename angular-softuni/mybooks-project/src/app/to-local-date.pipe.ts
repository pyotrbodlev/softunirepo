import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'toLocalDate'
})
export class ToLocalDatePipe implements PipeTransform {

  transform(value: string, pattern?: string): unknown {
    return new Date(value).toLocaleString();
  }

}
