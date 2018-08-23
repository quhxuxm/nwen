import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Command} from '../rich-text-editor-command-bus/command';
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

  private static generateCommand(name: string, value: string, showUi: boolean, isRangeCommand?: boolean,
                                 clearContext?: boolean,
                                 callback?: (affectedStartNode: Node, affectedEndNode: Node) => void): Command {
    const cmd = new Command();
    cmd.name = name;
    cmd.value = value;
    cmd.showUi = showUi;
    cmd.callback = callback;
    return cmd;
  }

  ngOnInit() {
  }

  onBold(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('bold', null, false, false));
  }

  onItalic(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('italic', null, false, false));
  }

  onFontSize(value: string, event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('fontSize', value, false, true, true,
      (affectedStartNode, affectedEndNode) => {
        console.log('==============affectedStartNode=============');
        console.log(affectedStartNode);
        if (affectedStartNode) {
          affectedStartNode.parentElement.removeAttribute('size');
          affectedStartNode.parentElement.style.fontSize = value;
        }
        console.log('==============affectedEndNode=============');
        console.log(affectedEndNode);
        if (affectedEndNode) {
          affectedEndNode.parentElement.removeAttribute('size');
          affectedEndNode.parentElement.style.fontSize = value;
        }
      }));
  }

  onHeading(value: string, event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('formatblock', value, false, false));
  }

  onClear(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('removeFormat', null, false, false));
  }

  onDelete(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('delete', null, false, false));
  }

  onQuote(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('formatblock', 'blockquote', false, false));
  }

  onParagraph(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('formatblock', 'p', false, false));
  }

  onImage(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('insertHTML',
      '<div class="test"><img src="/assets/image/1"></div>', false, false));
  }
}
