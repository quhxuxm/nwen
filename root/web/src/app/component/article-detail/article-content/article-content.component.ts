import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'nwen-article-content',
  templateUrl: './article-content.component.html',
  styleUrls: ['./article-content.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ArticleContentComponent implements OnInit {
  @Input()
  content: string;

  constructor() {
  }

  ngOnInit() {
  }
}
