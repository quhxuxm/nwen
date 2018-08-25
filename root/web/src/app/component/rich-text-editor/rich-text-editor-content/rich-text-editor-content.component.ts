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
    const targetContentDomElement = <HTMLDivElement>this.contentContainer.nativeElement;
    targetContentDomElement.innerHTML = `<div><br/></div>`;
    this.content = targetContentDomElement.innerHTML;
    targetContentDomElement.focus();
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
      if (command.name) {
        document.execCommand(command.name, command.showUi, command.value);
      }
      if (command.callback) {
        command.callback(targetContentDomElement, document.getSelection());
      }
    });
  }

  onBlur() {
    this.commandBus.commandContext = new CommandContext();
    if (document.getSelection()) {
      this.commandBus.commandContext.range = document.getSelection().getRangeAt(0);
    }
  }
}
