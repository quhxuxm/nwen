import {Component, Input, OnInit} from '@angular/core';
import {UrlService} from '../../../service/url.service';
import {Comment} from '../../../vo/comment';

@Component({
  selector: 'nwen-comment-viewer',
  templateUrl: './comment-viewer.component.html',
  styleUrls: ['./comment-viewer.component.scss']
})
export class CommentViewerComponent implements OnInit {
  @Input()
  comment: Comment;
  commentIconImgUrl: string;

  constructor(private urlService: UrlService) {
  }

  ngOnInit() {
    this.commentIconImgUrl = this.urlService.generateImageUrl(this.comment.commenterIconImgId.toString());
  }
}
