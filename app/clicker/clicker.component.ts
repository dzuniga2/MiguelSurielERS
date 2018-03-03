import { Component } from '@angular/core';


@Component({
    selector: 'app-clicker',
    templateUrl: './clicker.component.html',
    styleUrls: [
        './clicker.component.css'
    ]
})
export class ClickerComponent {
    counter = 0;

constructor() {

}
  increment(count: number): void {
      this.counter += count;
  }
}