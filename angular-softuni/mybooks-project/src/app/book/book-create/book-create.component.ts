import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.scss']
})
export class BookCreateComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    if (!sessionStorage.getItem('authtoken')) {
      this.router.navigate(['/']);
    }
  }

}
