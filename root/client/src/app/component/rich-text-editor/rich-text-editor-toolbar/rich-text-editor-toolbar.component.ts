import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Command, CommandCallbackType, CommandContext} from '../rich-text-editor-command-bus/command';
import {CommandBusService} from '../rich-text-editor-command-bus/command-bus.service';

enum MediaType {
  IMAGE, AUDIO, VIDEO
}

type FileConvertResultHandler = (result: string) => void;

@Component({
  selector: 'nwen-rich-text-editor-toolbar',
  templateUrl: './rich-text-editor-toolbar.component.html',
  styleUrls: ['./rich-text-editor-toolbar.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RichTextEditorToolbarComponent implements OnInit {
  constructor(private commandBus: CommandBusService) {
  }

  private static createImageLoadingElement(): Element {
    const imgLoadingContainerElement = document.createElement(`div`);
    imgLoadingContainerElement.setAttribute('class', 'rich-text-editor-image-loading-container');
    const progressElement = document.createElement(`div`);
    progressElement.setAttribute('class', 'rich-text-editor-image-loading-progress');
    progressElement.setAttribute('contenteditable', 'false');
    imgLoadingContainerElement.insertBefore(progressElement, null);
    return imgLoadingContainerElement;
  }

  private static convertFileToBase64(fileInputElement: HTMLInputElement, callback: FileConvertResultHandler) {
    const fileToUpload: File = fileInputElement.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(fileToUpload);
    reader.onload = function (e) {
      callback(this.result);
    };
  }

  private static loadImage(fileInputElement: HTMLInputElement, loadingElement: Element) {
    RichTextEditorToolbarComponent.convertFileToBase64(fileInputElement, result => {
      loadingElement.setAttribute('class', loadingElement.getAttribute('class') + ' full');
      setTimeout(() => {
        const uploadedImageElement = document.createElement('img');
        uploadedImageElement.setAttribute('class', 'article-detail-content-img');
        uploadedImageElement.setAttribute('src', result);
        loadingElement.parentElement.replaceChild(uploadedImageElement, loadingElement);
        const emptyBr = document.createElement('br');
        uploadedImageElement.parentElement.appendChild(emptyBr);
      }, 1200);
    });
  }

  private static loadAudio(fileInputElement: HTMLInputElement, loadingElement: Element) {
    RichTextEditorToolbarComponent.convertFileToBase64(fileInputElement, result => {
      loadingElement.setAttribute('class', loadingElement.getAttribute('class') + ' full');
      setTimeout(() => {
        const uploadedAudioElement = document.createElement('audio');
        uploadedAudioElement.setAttribute('class', 'article-detail-content-audio');
        uploadedAudioElement.setAttribute('src', result);
        uploadedAudioElement.setAttribute('controls', '');
        loadingElement.parentElement.replaceChild(uploadedAudioElement, loadingElement);
        const emptyBr = document.createElement('br');
        uploadedAudioElement.parentElement.appendChild(emptyBr);
      }, 1200);
    });
  }

  private static loadVideo(fileInputElement: HTMLInputElement, loadingElement: Element) {
    RichTextEditorToolbarComponent.convertFileToBase64(fileInputElement, result => {
      loadingElement.setAttribute('class', loadingElement.getAttribute('class') + ' full');
      setTimeout(() => {
        const uploadedVideoElement = document.createElement('video');
        uploadedVideoElement.setAttribute('class', 'article-detail-content-video');
        uploadedVideoElement.setAttribute('src', result);
        uploadedVideoElement.setAttribute('controls', '');
        loadingElement.parentElement.replaceChild(uploadedVideoElement, loadingElement);
        const emptyBr = document.createElement('br');
        uploadedVideoElement.parentElement.appendChild(emptyBr);
      }, 1200);
    });
  }

  private generateCommand(name: string, value: string,
                          callback?: CommandCallbackType): Command {
    const cmd = new Command();
    cmd.name = name;
    cmd.value = value;
    cmd.showUi = false;
    cmd.callback = callback;
    if (this.commandBus.commandContext) {
      cmd.context = this.commandBus.commandContext;
    } else {
      cmd.context = new CommandContext();
    }
    return cmd;
  }

  ngOnInit() {
  }

  onSave(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand(null, null, editorContainerElement => {
      alert(editorContainerElement.innerHTML);
    }));
  }

  onBold(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('bold', null, editorContainerElement => {
      console.log(editorContainerElement);
    }));
  }

  onItalic(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('italic', null));
  }

  onHeading(value: string, event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('formatblock', value, editorContainerElement => {
      console.log(editorContainerElement);
    }));
  }

  onClear(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('removeFormat', null, editorContainerElement => {
      console.log(editorContainerElement);
    }));
  }

  onDelete(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('delete', null, editorContainerElement => {
      console.log(editorContainerElement);
    }));
  }

  onQuote(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('formatblock', 'blockquote', editorContainerElement => {
      console.log(editorContainerElement);
    }));
  }

  onParagraph(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('formatblock', 'p', editorContainerElement => {
      console.log(editorContainerElement);
    }));
  }

  onUploadClick(event: Event, fileInputElement: HTMLInputElement) {
    event.preventDefault();
    fileInputElement.click();
  }

  onUploadFile(event: Event, type: MediaType, fileInputElement: HTMLInputElement) {
    event.preventDefault();
    // Set the place holder
    this.commandBus.sendCommand(this.generateCommand(null,
      null, (editorContainerElement, selection) => {
        if (selection.rangeCount === 0) {
          editorContainerElement.focus();
        } else {
          selection.collapse(selection.focusNode, selection.focusOffset);
        }
        document.execCommand('insertHTML', false, '<br class="rich-text-editor-content-place-holder"/>');
        const placeHolderElement = editorContainerElement.querySelector('br.rich-text-editor-content-place-holder');
        const loadingElement = RichTextEditorToolbarComponent.createImageLoadingElement();
        if (placeHolderElement == null) {
          editorContainerElement.insertBefore(loadingElement, null);
        } else {
          placeHolderElement.parentElement.replaceChild(loadingElement, placeHolderElement);
        }
        switch (type) {
          case MediaType.IMAGE:
            RichTextEditorToolbarComponent.loadImage(fileInputElement, loadingElement);
            break;
          case MediaType.AUDIO:
            RichTextEditorToolbarComponent.loadAudio(fileInputElement, loadingElement);
            break;
          case MediaType.VIDEO:
            RichTextEditorToolbarComponent.loadVideo(fileInputElement, loadingElement);
            break;
          default:
            break;
        }
        fileInputElement.value = '';
      }));
  }
}
