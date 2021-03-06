import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {ApiRequest} from '../vo/api/request/ApiRequest';
import {ApiResponse} from '../vo/api/response/ApiResponse';
import {ExceptionPayload} from '../vo/api/response/ExceptionPayload';
import {SecurityService} from './security.service';

export type ApiResponseHandler<ResponsePayloadType> = (response: ApiResponse<ResponsePayloadType>) => void;
export type ApiExceptionHandler = (exception: ExceptionPayload) => void;
export type ApiInvokeCompleteHandler = () => void;
type StringMap = { [name: string]: string };

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private _httpClient: HttpClient, private _securityService: SecurityService) {
  }

  get<ResponsePayloadType>(url: string, headers: StringMap, params: StringMap,
                           responseHandler?: ApiResponseHandler<ResponsePayloadType>,
                           exceptionHandler?: ApiExceptionHandler, invokeCompleteHandler?: ApiInvokeCompleteHandler
  ) {
    if (headers == null) {
      headers = {}
    }
    if (params == null) {
      params = {}
    }
    if (!this._securityService.securityContext.jwtToken) {
      this._securityService.securityContext.jwtToken = localStorage.getItem('JWT_TOKEN');
    }
    if (this._securityService.securityContext.jwtToken) {
      headers['Authorization'] = this._securityService.securityContext.jwtToken;
    }
    this._httpClient.get(url, {
      headers: headers,
      params: params
    }).subscribe((response: HttpResponse<ApiResponse<ResponsePayloadType>>) => {
      if (responseHandler) {
        responseHandler(response.body);
      }
    }, (errorResponse: HttpErrorResponse) => {
      if (errorResponse.status == 401) {
        console.log('Security error happen, redirect to login page.');
        return;
      }
      const apiExceptionResponse = <ApiResponse<ExceptionPayload>>(errorResponse.error);
      if (exceptionHandler && apiExceptionResponse) {
        if (!apiExceptionResponse.payload) {
          console.log('Unknown error.');
          return;
        }
        exceptionHandler(apiExceptionResponse.payload);
      }
    }, () => {
      if (invokeCompleteHandler) {
        invokeCompleteHandler();
      }
    })
  }

  post<RequestPayloadType, ResponsePayloadType>(url: string, headers: StringMap, params: StringMap,
                                                body?: ApiRequest<RequestPayloadType>,
                                                responseHandler?: ApiResponseHandler<ResponsePayloadType>,
                                                exceptionHandler?: ApiExceptionHandler,
                                                invokeCompleteHandler?: ApiInvokeCompleteHandler
  ) {
    if (headers == null) {
      headers = {}
    }
    if (params == null) {
      params = {}
    }
    if (!this._securityService.securityContext.jwtToken) {
      this._securityService.securityContext.jwtToken = localStorage.getItem('JWT_TOKEN');
    }
    if (this._securityService.securityContext.jwtToken) {
      headers['Authorization'] = this._securityService.securityContext.jwtToken;
    }
    this._httpClient.post(url, body, {
      headers: headers,
      params: params
    }).subscribe((response: ApiResponse<ResponsePayloadType>) => {
      console.log(response);
      if (responseHandler) {
        responseHandler(response);
      }
    }, (errorResponse: HttpErrorResponse) => {
      if (errorResponse.status == 401) {
        console.log('Security error happen, redirect to login page.');
        return;
      }
      const apiExceptionResponse = <ApiResponse<ExceptionPayload>>(errorResponse.error);
      if (exceptionHandler) {
        exceptionHandler(apiExceptionResponse.payload);
      }
    }, () => {
      if (invokeCompleteHandler) {
        invokeCompleteHandler();
      }
    })
  }
}


