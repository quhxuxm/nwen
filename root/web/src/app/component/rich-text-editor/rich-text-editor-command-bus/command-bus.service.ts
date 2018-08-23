import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Command} from './command';

@Injectable({
  providedIn: 'root'
})
export class CommandBusService {
  private commandSubject = new Subject<Command>();

  constructor() {
  }

  public sendCommand(command: Command) {
    this.commandSubject.next(command);
  }

  public receiveCommand(callback: (command: Command) => void) {
    this.commandSubject.subscribe(command => {
      callback(command);
    });
  }
}
