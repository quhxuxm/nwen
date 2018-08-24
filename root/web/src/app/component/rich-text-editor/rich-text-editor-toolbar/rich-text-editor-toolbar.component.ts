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
                          callback?: (currentSelection: Selection) => void): Command {
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

  onImage(event: Event) {
    event.preventDefault();
    this.commandBus.sendCommand(this.generateCommand('insertHTML',
      `<p class='temp' />`, range => {
        console.log(range);
      }));
  }
}
