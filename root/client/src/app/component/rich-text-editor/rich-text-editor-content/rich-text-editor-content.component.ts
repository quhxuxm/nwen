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
      const targetContentDomElement = <HTMLElement>this.contentContainer.nativeElement;
      const selection = document.getSelection();
      selection.removeAllRanges();
      const selectedRange = command.context.range;
      if (selectedRange) {
        selection.addRange(selectedRange);
      }
      if (command.name) {
        document.execCommand(command.name, command.showUi, command.value);
      }
      if (command.callback) {
        command.callback(targetContentDomElement, selection);
      }
    });
  }

  onBlur() {
    this.commandBus.commandContext = new CommandContext();
    const selection = document.getSelection();
    if (selection.rangeCount > 0) {
      this.commandBus.commandContext.range = selection.getRangeAt(0);
    }
  }

  onPaste(event: Event) {
    event.preventDefault();
    const text = event['clipboardData'].getData('text/plain');
    document.execCommand('inserttext', false, text);
    return false;
  }

  getContent() {
    return (<HTMLDivElement>this.contentContainer.nativeElement).innerHTML;
  }
  getText() {
    return (<HTMLDivElement>this.contentContainer.nativeElement).innerText;
  }
}
