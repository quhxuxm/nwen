export class SecurityContext {
  private _jwtToken: string;
  private _expireTime: number;

  constructor() {
    this._jwtToken = localStorage.getItem('JWT_TOKEN');
    this._expireTime = parseInt(localStorage.getItem('JWT_EXPIRE_TIME'));
    if (!this._jwtToken) {
      this._jwtToken = null;
    }
    if (!this._expireTime) {
      this._expireTime = null;
    }
  }

  get jwtToken(): string {
    return this._jwtToken;
  }

  set jwtToken(value: string) {
    this._jwtToken = value;
  }

  get expireTime(): number {
    return this._expireTime;
  }

  set expireTime(value: number) {
    this._expireTime = value;
  }

  clear() {
    this._jwtToken = null;
    this._expireTime = null;
    localStorage.clear();
  }

  isAuthenticated(): boolean {
    if (!this._jwtToken) {
      return false;
    }
    const currentDate = new Date();
    const expireDate = new Date();
    expireDate.setTime(this._expireTime);
    return currentDate <= expireDate;
  }
}
