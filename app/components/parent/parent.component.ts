import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.css']
})
export class ParentComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  toRandomChild() {
    const childNumber = Math.floor(Math.random() * 6) + 1;
    this.router.navigateByUrl(`/parent/child${childNumber}`);
  }

}
