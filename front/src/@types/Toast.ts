import { Dispatch, SetStateAction } from "react";

export enum ToastStatus {
  SUCCESS = "SUCCESS",
  ERROR = "ERROR",
  IDLE = "IDLE",
}

export interface INotify {
  message: string;
  status: string;
}

export interface IToastContext {
  notify: (status: ToastStatus, message: string) => void;
  setToastProperties: Dispatch<SetStateAction<INotify>>;
  alertProperties: INotify;
}
