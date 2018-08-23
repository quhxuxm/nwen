import {Component, OnInit} from '@angular/core';
import {Command} from '../rich-text-editor-command-bus/command';
import {CommandBusService} from '../rich-text-editor-command-bus/command-bus.service';

@Component({
  selector: 'nwen-rich-text-editor-toolbar',
  templateUrl: './rich-text-editor-toolbar.component.html',
  styleUrls: ['./rich-text-editor-toolbar.component.scss']
})
export class RichTextEditorToolbarComponent implements OnInit {
  constructor(private commandBus: CommandBusService) {
  }

  private static generateCommand(name: string, value: string, showUi: boolean,
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

  onBold() {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('bold', null, false));
  }

  onItalic() {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('italic', null, false));
  }

  onFontSize(value: string) {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('fontSize', value, false,
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

  onHeading(value: string) {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('formatblock', value, false));
  }

  onClear() {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('removeFormat', null, false));
  }

  onDelete() {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('delete', null, false));
  }

  onQuote() {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('formatblock', 'blockquote', false));
  }

  onParagraph() {
    this.commandBus.sendCommand(RichTextEditorToolbarComponent.generateCommand('formatblock', 'p', false));
  }
}
