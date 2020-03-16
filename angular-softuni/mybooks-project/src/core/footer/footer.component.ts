import {Component} from '@angular/core';
import {LoaderService} from '../../shared/loader/loader.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {
  constructor(private loader: LoaderService) {
  }

  get show() {
    return !this.loader.isLoading;
  }
}
