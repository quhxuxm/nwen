import {AfterContentInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {CommandContext} from '../rich-text-editor-command-bus/command';
import {CommandBusService} from '../rich-text-editor-command-bus/command-bus.service';

@Component({
  selector: 'nwen-rich-text-editor-content',
  templateUrl: './rich-text-editor-content.component.html',
  styleUrls: ['./rich-text-editor-content.component.scss']
})
export class RichTextEditorContentComponent implements AfterContentInit, OnInit {
  @ViewChild('contentContainer', {read: ElementRef})
  private contentContainer: ElementRef;
  private currentRange: Range;
  content: string;

  constructor(private commandBus: CommandBusService, private element: ElementRef) {
  }

  ngOnInit(): void {
    const targetContentDomElement = this.contentContainer.nativeElement;
    targetContentDomElement.innerHTML = `<div><br/></div>`;
    this.content = targetContentDomElement.innerHTML;
  }

  ngAfterContentInit() {
    this.commandBus.receiveCommand(command => {
      const targetContentDomElement = <Element>this.contentContainer.nativeElement;
      if (document.getSelection()) {
        document.getSelection().removeAllRanges();
      }
      const selectedRange = command.context.range;
      if (selectedRange) {
        document.getSelection().addRange(selectedRange);
      }
      document.execCommand(command.name, command.showUi, command.value);
      if (command.callback) {
        command.callback(document.getSelection());
      }
      // const targetContentDomElement = this.contentContainer.nativeElement;
      // this.content = targetContentDomElement.innerHTML;
      // targetContentDomElement.focus();
      // if (command.rangeCommand) {
      //   if (typeof document.body['createTextRange'] !== 'undefined') {
      //     // For windows only
      //     const textRange = document.body['createTextRange']();
      //     textRange.moveToElementText(targetContentDomElement);
      //     textRange.collapse(false);
      //     textRange.select();
      //   } else {
      //     // For others
      //     console.log('============== this.currentRange ==============');
      //     console.log(this.currentRange);
      //     console.log('this.currentRange.startOffset = ' + this.currentRange.startOffset);
      //     console.log('============== this.currentRange.startContainer ==============');
      //     console.log(this.currentRange.startContainer);
      //     const newSelectRange = document.createRange();
      //     newSelectRange.setStart(this.currentRange.startContainer, this.currentRange.startOffset);
      //     newSelectRange.setEnd(this.currentRange.endContainer, this.currentRange.endOffset);
      //     console.log('============== newSelectRange ==============');
      //     console.log(newSelectRange);
      //     document.getSelection().removeAllRanges();
      //     document.getSelection().addRange(newSelectRange);
      //     document.execCommand(command.name, command.showUi, command.value);
      //     const affectedRange = document.getSelection().getRangeAt(0);
      //     if (command.callback) {
      //       command.callback(affectedRange.startContainer, affectedRange.endContainer);
      //     }
      //     if (command.clearContext) {
      //       this.currentRange = null;
      //     }
      //   }
      // } else {
      //   document.execCommand(command.name, command.showUi, command.value);
      // }
    });
  }

  onBlur() {
    this.commandBus.commandContext = new CommandContext();
    if (document.getSelection()) {
      this.commandBus.commandContext.range = document.getSelection().getRangeAt(0);
    }
  }
}
