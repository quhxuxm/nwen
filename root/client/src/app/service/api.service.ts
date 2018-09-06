import {HttpClient, HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {ApiRequest} from '../vo/api/request/ApiRequest';
import {ApiResponse} from '../vo/api/response/ApiResponse';
import {ExceptionPayload} from '../vo/api/response/ExceptionPayload';
import {SecurityContext} from '../vo/security-context';

export type ApiResponseHandler<ResponsePayloadType> = (response: ApiResponse<ResponsePayloadType>) => void;
export type ApiExceptionHandler = (exception: ExceptionPayload) => void;
export type ApiInvokeCompleteHandler = () => void;
type StringMap = { [name: string]: string };

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private _securityContext: SecurityContext;

  constructor(private _httpClient: HttpClient) {
    this._securityContext = new SecurityContext();
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
    if (this._securityContext.jwtToken) {
      headers['Authorization'] = this._securityContext.jwtToken;
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
    if (this._securityContext.jwtToken) {
      headers['Authorization'] = this._securityContext.jwtToken;
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

  get securityContext(): SecurityContext {
    return this._securityContext;
  }
}


