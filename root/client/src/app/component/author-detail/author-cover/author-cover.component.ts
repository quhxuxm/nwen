import {Component, Input, OnInit} from '@angular/core';
import {UrlService} from '../../../service/url.service';
import {AuthorDetail} from '../../../vo/author-detail';

@Component({
  selector: 'nwen-author-cover',
  templateUrl: './author-cover.component.html',
  styleUrls: ['./author-cover.component.scss']
})
export class AuthorCoverComponent implements OnInit {
  @Input()
  author: AuthorDetail;
  authorIconImageUrl: string;

  constructor(private urlService: UrlService) {
  }

  ngOnInit() {
    this.authorIconImageUrl = this.urlService.generateImageUrl(this.author.iconImageId.toString());
  }
}
