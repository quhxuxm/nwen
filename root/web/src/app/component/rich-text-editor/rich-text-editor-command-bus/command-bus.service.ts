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

  public send(command: Command) {
    this.commandSubject.next(command)
  }

  public receive(callback: (command: Command) => void) {
    this.commandSubject.subscribe(command => {
      callback(command);
    })
  }
}
