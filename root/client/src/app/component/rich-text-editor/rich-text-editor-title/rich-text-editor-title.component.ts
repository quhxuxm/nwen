import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'nwen-rich-text-editor-title',
  templateUrl: './rich-text-editor-title.component.html',
  styleUrls: ['./rich-text-editor-title.component.scss']
})
export class RichTextEditorTitleComponent implements OnInit {
  title: string;

  constructor() {
    this.title = '';
  }

  ngOnInit() {
  }
}
