import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'nwen-jumbotron',
  templateUrl: './jumbotron.component.html',
  styleUrls: ['./jumbotron.component.scss']
})
export class JumbotronComponent implements OnInit {
  @Input()
  backgroundImageUrl: string;

  constructor() {
  }

  ngOnInit() {
  }
}
