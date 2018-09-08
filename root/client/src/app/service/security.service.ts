import {Injectable} from '@angular/core';
import {SecurityContext} from '../vo/security-context';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private _securityContext: SecurityContext;

  constructor() {
    this._securityContext = new SecurityContext();
  }

  get securityContext(): SecurityContext {
    return this._securityContext;
  }
}
