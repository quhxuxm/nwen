export class ApiRequest<PayloadType> {
  header: Map<string, string>;
  payload: PayloadType;

  constructor() {
    this.header = new Map();
    this.payload = null;
  }
}
