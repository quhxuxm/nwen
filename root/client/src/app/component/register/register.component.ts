import {Component, OnInit, ViewChild} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
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
  nickname: string;
  @ViewChild('registerForm')
  registerForm: FormGroup;

  constructor(private apiService: ApiService, private router: Router) {
  }

  ngOnInit() {
  }

  public register() {
    const registerRequest: ApiRequest<RegisterRequestPayload> = new ApiRequest();
    const payload = new RegisterRequestPayload();
    payload.token = this.token;
    payload.password = this.password;
    payload.nickname = this.nickname;
    registerRequest.payload = payload;
    const apiResponseHandler: ApiResponseHandler<RegisterResponsePayload> = response => {
      console.log(response.payload);
      this.router.navigateByUrl('/login');
    };
    const apiExceptionHandler: ApiExceptionHandler = response => {
      //Token server errors
      if ('REGISTER_TOKEN_IS_EMPTY_ERROR' === response.code) {
        this.registerForm.controls['token'].setErrors({
          'server-empty': response.code
        });
        return;
      }
      if ('REGISTER_TOKEN_FORMAT_INCORRECT' === response.code) {
        this.registerForm.controls['token'].setErrors({
          'server-format': response.code
        });
        return;
      }
      if ('REGISTER_TOKEN_EXIST_ERROR' === response.code) {
        this.registerForm.controls['token'].setErrors({
          'server-unique': response.code
        });
        return;
      }
      //Password server errors
      if ('REGISTER_PASSWORD_IS_EMPTY_ERROR' === response.code) {
        this.registerForm.controls['password'].setErrors({
          'server-empty': response.code
        });
        return;
      }
      if ('REGISTER_PASSWORD_FORMAT_INCORRECT' === response.code) {
        this.registerForm.controls['password'].setErrors({
          'server-format': response.code
        });
        return;
      }
      //Nick name server errors
      if ('REGISTER_NICKNAME_IS_EMPTY_ERROR' === response.code) {
        this.registerForm.controls['nickname'].setErrors({
          'server-empty': response.code
        });
        return;
      }
      if ('REGISTER_NICKNAME_FORMAT_INCORRECT' === response.code) {
        this.registerForm.controls['nickname'].setErrors({
          'server-format': response.code
        });
        return;
      }
      if ('REGISTER_NICKNAME_EXIST_ERROR' === response.code) {
        this.registerForm.controls['nickname'].setErrors({
          'server-unique': response.code
        });
        return;
      }
      if ('REGISTER_NICKNAME_MAX_LENGTH_INCORRECT' === response.code) {
        this.registerForm.controls['nickname'].setErrors({
          'server-max-length': response.code
        });
        return;
      }
      if ('REGISTER_NICKNAME_MIN_LENGTH_INCORRECT' === response.code) {
        this.registerForm.controls['nickname'].setErrors({
          'server-min-length': response.code
        });
        return;
      }
    };
    this.apiService.post('/api/register', null, null, registerRequest, apiResponseHandler, apiExceptionHandler);
  }
}


