import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {
  private _jwtToken: string;

  constructor() {
  }

  get jwtToken() {
    return this._jwtToken;
  }

  set jwtToken(jwtToken: string) {
    this._jwtToken = jwtToken;
  }
}
