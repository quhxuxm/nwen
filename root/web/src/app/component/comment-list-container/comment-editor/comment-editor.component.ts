import {Component, Input, OnInit} from '@angular/core';
import {UrlService} from '../../../service/url.service';

@Component({
  selector: 'nwen-comment-editor',
  templateUrl: './comment-editor.component.html',
  styleUrls: ['./comment-editor.component.scss']
})
export class CommentEditorComponent implements OnInit {
  @Input()
  commenterId: number;
  commenterIconUrl: string;

  constructor(private urlService: UrlService) {
  }

  ngOnInit() {
    this.commenterIconUrl = this.urlService.generateImageUrl(this.commenterId.toString());
  }
}
