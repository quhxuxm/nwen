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

  ngOnInit() {
  }

  onBold() {
    const cmd = new Command();
    cmd.name = 'bold';
    this.commandBus.send(cmd);
  }

  onItalic() {
    const cmd = new Command();
    cmd.name = 'italic';
    this.commandBus.send(cmd);
  }
}
