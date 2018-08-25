import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Command, CommandContext} from '../rich-text-editor-command-bus/command';
import {CommandBusService} from '../rich-text-editor-command-bus/command-bus.service';

@Component({
  selector: 'nwen-rich-text-editor-toolbar',
  templateUrl: './rich-text-editor-toolbar.component.html',
  styleUrls: ['./rich-text-editor-toolbar.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RichTextEditorToolbarComponent implements OnInit {
  constructor(private commandBus: CommandBusService) {
  }

  private generateCommand(name: string, value: string,
                          callback?: (editorContainerElement: Element, currentSelection: Selection) => void): Command {
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

  onBold(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('bold', null, range => {
      console.log(range);
    }));
  }

  onItalic(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('italic', null));
  }

  onHeading(value: string, event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('formatblock', value, range => {
      console.log(range);
    }));
  }

  onClear(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('removeFormat', null, range => {
      console.log(range);
    }));
  }

  onDelete(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('delete', null, range => {
      console.log(range);
    }));
  }

  onQuote(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('formatblock', 'blockquote', range => {
      console.log(range);
    }));
  }

  onParagraph(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('formatblock', 'p', range => {
      console.log(range);
    }));
  }

  onImageStep1(event: Event, fileInputElement) {
    event.preventDefault();
    fileInputElement.click();
  }

  onImageStep2(event: Event, fileInputElement: HTMLInputElement) {

    // Set the place holder
    this.commandBus.sendCommand(this.generateCommand(null,
      null, (editorContainerElement, selection) => {
        // if (selection.anchorNode.nodeType == Node.TEXT_NODE) {
        selection.collapse(selection.focusNode, selection.focusOffset);
        document.execCommand('insertHTML', false, '<br class="rich-text-editor-content-place-holder"/>');
        const placeHolderElement = editorContainerElement.querySelector('br.rich-text-editor-content-place-holder');
        const imageLoadingElement = RichTextEditorToolbarComponent.createImageLoadingElement(fileInputElement.value);
        placeHolderElement.parentElement.replaceChild(imageLoadingElement, placeHolderElement);
        RichTextEditorToolbarComponent.loadImage(fileInputElement, imageLoadingElement);
        fileInputElement.value = '';
        // }
      }));
  }

  private static createImageLoadingElement(fileName: string): Element {
    const imgLoadingContainerElement = document.createElement(`div`);
    imgLoadingContainerElement.setAttribute('class', 'rich-text-editor-image-loading-container');
    imgLoadingContainerElement.innerHTML = fileName;
    return imgLoadingContainerElement;
  }

  private static loadImage(fileInputElement: HTMLInputElement, imageLoadingElement: Element) {
    const fileToUpload: File = fileInputElement.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(fileToUpload);
    reader.onload = function (e) {
      var uploadedImageElement = document.createElement('img');
      uploadedImageElement.setAttribute('class', 'article-detail-content-img')
      uploadedImageElement.setAttribute('src', this.result);
      imageLoadingElement.parentElement.replaceChild(uploadedImageElement, imageLoadingElement);
    };
    reader.onprogress = function (e) {
    };
  }
}
