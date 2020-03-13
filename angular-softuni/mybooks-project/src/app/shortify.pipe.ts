import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'shortify'
})
export class ShortifyPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    return value.substring(0, 255) + '...';
  }

}
