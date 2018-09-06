import {Component, OnInit, ViewChild} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {ApiExceptionHandler, ApiResponseHandler, ApiService} from '../../service/api.service';
import {ApiRequest} from '../../vo/api/request/ApiRequest';
import {LoginRequestPayload} from '../../vo/login-request-payload';
import {LoginResponsePayload} from '../../vo/login-response-payload';

@Component({
  selector: 'nwen-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  token: string;
  password: string;
  @ViewChild('loginForm')
  loginForm: FormGroup;

  constructor(private apiService: ApiService, private router: Router) {
  }

  ngOnInit() {
  }

  public login() {
    const loginApiRequest: ApiRequest<LoginRequestPayload> = new ApiRequest();
    const payload = new LoginRequestPayload();
    payload.token = this.token;
    payload.password = this.password;
    loginApiRequest.payload = payload;
    const apiResponseHandler: ApiResponseHandler<LoginResponsePayload> = response => {
      console.log(response.payload.jwtToken);
      this.apiService.securityContext.jwtToken = response.payload.jwtToken;
      this.router.navigateByUrl('/home');
    };
    const apiExceptionHandler: ApiExceptionHandler = response => {
      //Token server errors
      if ('LOGIN_TOKEN_IS_EMPTY_ERROR' === response.code) {
        this.loginForm.controls['token'].setErrors({
          'server': response.code
        });
        return;
      }
      if ('LOGIN_TOKEN_FORMAT_INCORRECT' === response.code) {
        this.loginForm.controls['token'].setErrors({
          'server': response.code
        });
        return;
      }
      if ('LOGIN_TOKEN_EXIST_ERROR' === response.code) {
        this.loginForm.controls['token'].setErrors({
          'server': response.code
        });
        return;
      }
      if ('AUTHENTICATION_TOKEN_NOT_EXIST' === response.code) {
        this.loginForm.controls['token'].setErrors({
          'server': response.code
        });
        return;
      }
      //Password server errors
      if ('LOGIN_PASSWORD_IS_EMPTY_ERROR' === response.code) {
        this.loginForm.controls['password'].setErrors({
          'server': response.code
        });
        return;
      }
      if ('LOGIN_PASSWORD_FORMAT_INCORRECT' === response.code) {
        this.loginForm.controls['password'].setErrors({
          'server': response.code
        });
        return;
      }
      if ('AUTHENTICATION_PASSWORD_NOT_MATCH' === response.code) {
        this.loginForm.controls['password'].setErrors({
          'server': response.code
        });
        return;
      }
    };
    this.apiService.post('/api/authenticate', null, null,
      loginApiRequest, apiResponseHandler, apiExceptionHandler);
  }
}
