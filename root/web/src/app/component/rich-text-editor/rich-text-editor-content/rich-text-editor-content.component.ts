import {AfterContentInit, Component, ElementRef, ViewChild, ViewEncapsulation} from '@angular/core';
import {CommandBusService} from '../rich-text-editor-command-bus/command-bus.service';

@Component({
  selector: 'nwen-rich-text-editor-content',
  templateUrl: './rich-text-editor-content.component.html',
  styleUrls: ['./rich-text-editor-content.component.scss']
})
export class RichTextEditorContentComponent implements AfterContentInit {
  @ViewChild('contentContainer', {read: ElementRef})
  private contentContainer: ElementRef;
  content: string;

  constructor(private commandBus: CommandBusService, private element: ElementRef) {
  }

  ngAfterContentInit() {
    this.commandBus.receive(command => {
      this.content = this.contentContainer.nativeElement.innerHTML;
      this.contentContainer.nativeElement.focus();
      document.execCommand(command.name);
    })
  }
}
