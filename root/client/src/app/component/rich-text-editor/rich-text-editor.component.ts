import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {SecurityService} from '../../service/security.service';
import {ApiRequest} from '../../vo/api/request/ApiRequest';
import {SaveRichTextEditorContentPayload} from '../../vo/save-rich-text-editor-content-payload';
import {RichTextEditorContentComponent} from './rich-text-editor-content/rich-text-editor-content.component';

@Component({
  selector: 'nwen-rich-text-editor',
  templateUrl: './rich-text-editor.component.html',
  styleUrls: ['./rich-text-editor.component.scss']
})
export class RichTextEditorComponent implements OnInit {
  @ViewChild(RichTextEditorContentComponent)
  private richTextEditorContentComponent: RichTextEditorContentComponent;
  @Input()
  saveUrl: string;
  publishUrl: string;

  constructor(private apiService: ApiService,
              private securityService: SecurityService) {
  }

  ngOnInit() {
  }

  onSave() {
    const apiRequest: ApiRequest<SaveRichTextEditorContentPayload> = new ApiRequest();
    const payload: SaveRichTextEditorContentPayload = new SaveRichTextEditorContentPayload();
    apiRequest.payload = payload;
    payload.anthologyId = null;
    payload.content = this.richTextEditorContentComponent.getContent();
    payload.title = '标题';
    this.apiService.post(this.saveUrl, null, null, apiRequest);
  }

  onPublish() {
  }
}
