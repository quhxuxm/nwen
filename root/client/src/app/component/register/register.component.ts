import {Component, OnInit} from '@angular/core';
import {AbstractControl, ValidatorFn} from '@angular/forms';
import {ApiExceptionHandler, ApiResponseHandler, ApiService} from '../../service/api.service';
import {ApiRequest} from '../../vo/api/request/ApiRequest';
import {RegisterRequestPayload} from '../../vo/register-request-payload';
import {RegisterResponsePayload} from '../../vo/register-response-payload';

@Component({
  selector: 'nwen-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  token: string;
  password: string;
  nickName: string;
  tokenErrorCode: string;
  passwordErrorCode: string;
  nickNameErrorCode: string;

  constructor(private apiService: ApiService) {
    this.tokenErrorCode = null;
    this.passwordErrorCode = null;
    this.nickNameErrorCode = null;
  }

  ngOnInit() {
  }

  public register() {
    const registerRequest: ApiRequest<RegisterRequestPayload> = new ApiRequest();
    const payload = new RegisterRequestPayload();
    payload.token = this.token;
    payload.password = this.password;
    payload.nickName = this.nickName;
    registerRequest.payload = payload;
    const apiResponseHandler: ApiResponseHandler<RegisterResponsePayload> = response => {
      this.tokenErrorCode = null;
      console.log(response.payload)
    };
    const apiExceptionHandler: ApiExceptionHandler = response => {
      this.tokenErrorCode = response.code;
      console.log(response.code);
    };
    this.apiService.post('/api/register', null, null, registerRequest, apiResponseHandler, apiExceptionHandler);
  }

  private formatValidator(formatRe: RegExp): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const forbidden = formatRe.test(control.value);
      return forbidden ? {'forbiddenName': {value: control.value}} : null;
    };
  }
}


