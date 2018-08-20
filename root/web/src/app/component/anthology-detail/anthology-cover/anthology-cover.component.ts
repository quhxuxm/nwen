import {Component, OnInit} from '@angular/core';
import {UrlService} from '../../../service/url.service';
import {AnthologyDetail} from '../../../vo/anthology-detail';
import {Card} from '../../../vo/ui/card';

@Component({
  selector: 'nwen-anthology-cover',
  templateUrl: './anthology-cover.component.html',
  styleUrls: ['./anthology-cover.component.scss']
})
export class AnthologyCoverComponent implements OnInit {


  constructor(private urlService: UrlService) {

  }

  ngOnInit() {
  }
}
