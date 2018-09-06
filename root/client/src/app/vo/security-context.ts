export class SecurityContext {
  private _jwtToken: string;

  constructor() {
    this._jwtToken = null;
  }

  get jwtToken(): string {
    return this._jwtToken;
  }

  set jwtToken(value: string) {
    this._jwtToken = value;
  }
}
