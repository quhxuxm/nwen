import {Component, Input, OnInit} from '@angular/core';

type Size = '1x' | '1_5x' | '2x' | '3x' | '4x';

@Component({
  selector: 'nwen-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.scss']
})
export class LogoComponent implements OnInit {
  @Input()
  size: Size;
  @Input()
  url: string;
  width: string;

  constructor() {
    this.size = '1x';
    this.url = null;
  }

  ngOnInit() {
    if (this.size === '1x') {
      this.width = '25%';
      return;
    }
    if (this.size === '1_5x') {
      this.width = '37.5%';
      return;
    }
    if (this.size === '2x') {
      this.width = '50%';
      return;
    }
    if (this.size === '3x') {
      this.width = '75%';
      return;
    }
    if (this.size === '4x') {
      this.width = '100%';
      return;
    }
    this.width = '25%';
  }
}
