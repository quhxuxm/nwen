import {Component, Input, OnInit} from '@angular/core';
import {Comment} from '../../../vo/comment';

@Component({
  selector: 'nwen-comment-viewer',
  templateUrl: './comment-viewer.component.html',
  styleUrls: ['./comment-viewer.component.scss']
})
export class CommentViewerComponent implements OnInit {
  @Input()
  comment: Comment;

  constructor() {
  }

  ngOnInit() {
  }
}
