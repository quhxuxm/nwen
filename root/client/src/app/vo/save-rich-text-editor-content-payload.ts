export class SaveRichTextEditorContentPayload {
  private _anthologyId: string;
  private _content: string;
  private _title: string;

  get anthologyId(): string {
    return this._anthologyId;
  }

  set anthologyId(value: string) {
    this._anthologyId = value;
  }

  get content(): string {
    return this._content;
  }

  set content(value: string) {
    this._content = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }
}
