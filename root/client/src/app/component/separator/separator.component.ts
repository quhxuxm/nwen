import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'nwen-separator',
  templateUrl: './separator.component.html',
  styleUrls: ['./separator.component.scss']
})
export class SeparatorComponent implements OnInit {
  @Input()
  showLeftBorder: boolean;
  @Input()
  showRightBorder: boolean;
  @Input()
  showContent: boolean;

  constructor() {
    this.showLeftBorder = true;
    this.showRightBorder = true;
    this.showContent = false;
  }

  ngOnInit() {
  }
}
