import {Component, Input, OnInit} from '@angular/core';
import {AnthologyDetailService} from '../../../service/anthology-detail.service';
import {UrlService} from '../../../service/url.service';
import {AnthologyDetail} from '../../../vo/anthology-detail';

@Component({
  selector: 'nwen-anthology-cover',
  templateUrl: './anthology-cover.component.html',
  styleUrls: ['./anthology-cover.component.scss']
})
export class AnthologyCoverComponent implements OnInit {
  @Input()
  anthology: AnthologyDetail;
  authorIconImageUrl: string;

  constructor(private anthologyDetailService: AnthologyDetailService, private urlService: UrlService) {
  }

  ngOnInit() {
    this.anthology = this.anthologyDetailService.query(1);
    this.authorIconImageUrl = this.urlService.generateImageUrl(this.anthology.authorIconImgId.toString());
  }
}
