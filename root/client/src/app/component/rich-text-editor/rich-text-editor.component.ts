import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {ApiResponseHandler, ApiService} from '../../service/api.service';
import {SecurityService} from '../../service/security.service';
import {ApiRequest} from '../../vo/api/request/ApiRequest';
import {SaveRichTextEditorContentPayload} from '../../vo/save-rich-text-editor-content-payload';
import {SaveRichTextEditorResponsePayload} from '../../vo/save-rich-text-editor-response-payload';
import {RichTextEditorContentComponent} from './rich-text-editor-content/rich-text-editor-content.component';
import {RichTextEditorTitleComponent} from './rich-text-editor-title/rich-text-editor-title.component';

@Component({
  selector: 'nwen-rich-text-editor',
  templateUrl: './rich-text-editor.component.html',
  styleUrls: ['./rich-text-editor.component.scss']
})
export class RichTextEditorComponent implements OnInit {
  @ViewChild(RichTextEditorContentComponent)
  private richTextEditorContentComponent: RichTextEditorContentComponent;
  @ViewChild(RichTextEditorTitleComponent)
  private richTextEditorTitleComponent: RichTextEditorTitleComponent;
  @Input()
  saveUrl: string;
  publishUrl: string;
  private currentArticleId: string;

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
    payload.title = this.richTextEditorTitleComponent.title;
    payload.articleId = this.currentArticleId;
    let summary = this.richTextEditorContentComponent.getText();
    if (summary.length > 100) {
      summary = summary.substring(0, 100);
    }
    payload.summary = summary;
    const apiResponseHandler: ApiResponseHandler<SaveRichTextEditorResponsePayload> = response => {
      console.log(response.payload);
      this.currentArticleId = response.payload.articleId;
    };
    this.apiService.post(this.saveUrl, null, null, apiRequest, apiResponseHandler);
  }

  onPublish() {
  }
}
