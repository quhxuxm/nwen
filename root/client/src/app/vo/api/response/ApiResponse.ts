export class ApiResponse<PayloadType> {
  success: boolean;
  header: Map<string, string>;
  payload: PayloadType;

  constructor() {
    this.success = false;
    this.header = new Map();
    this.payload = null;
  }
}
