import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../../vo/comment';

@Component({
  selector: 'nwen-comment-list-container',
  templateUrl: './comment-list-container.component.html',
  styleUrls: ['./comment-list-container.component.scss']
})
export class CommentListContainerComponent implements OnInit {
  @Input()
  comments: Comment[];
  @Input()
  currentCommenterId: number;

  constructor() {
  }

  ngOnInit() {
  }
}
