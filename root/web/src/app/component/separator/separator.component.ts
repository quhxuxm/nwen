import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'nwen-separator',
  templateUrl: './separator.component.html',
  styleUrls: ['./separator.component.scss']
})
export class SeparatorComponent implements OnInit {
  @Input()
  label: string;

  constructor() {
  }

  ngOnInit() {
  }
}
