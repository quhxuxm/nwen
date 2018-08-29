import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Command, CommandContext} from './command';

@Injectable({
  providedIn: 'root'
})
export class CommandBusService {
  private _commandContext: CommandContext;
  private commandSubject = new Subject<Command>();

  constructor() {
  }

  public sendCommand(command: Command) {
    this.commandSubject.next(command);
    this.commandContext = null;
  }

  public receiveCommand(onReceiveCallback: (command: Command) => void) {
    this.commandSubject.subscribe(command => {
      onReceiveCallback(command);
    }, error => {
      console.error(error);
    });
  }

  get commandContext(): CommandContext {
    return this._commandContext;
  }

  set commandContext(value: CommandContext) {
    this._commandContext = value;
  }
}
