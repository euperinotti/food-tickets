import {
  INotify,
  IToastContext,
  ToastStatus,
} from "@/@types/Toast";
import { createContext, ReactNode, useState } from "react";
import { toast, ToastContainer, ToastOptions } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

interface ToastProviderProps {
  children: ReactNode;
}

export const ToastContext = createContext<IToastContext | undefined>(undefined);

const toastConfig: ToastOptions = {
  position: "top-right",
  autoClose: 3500,
  closeOnClick: true,
  draggable: false,
};

const getInitialState = () => {
  return {
    message: "",
    status: ToastStatus.IDLE,
  };
};

export const ToastProvider = ({ children }: ToastProviderProps) => {
  const [alertProperties, setToastProperties] =
    useState<INotify>(getInitialState);

  const notify = (status: ToastStatus, message: string) => {
    switch (status) {
      case ToastStatus.SUCCESS:
        toast.success(message, toastConfig);
        break;
      case ToastStatus.ERROR:
        toast.error(message, toastConfig);
        break;
      default:
        toast.info(message, toastConfig);
        break;
    }
  };

  return (
    <ToastContext.Provider
      value={{ notify, setToastProperties, alertProperties }}
    >
      {children}
      <ToastContainer />
    </ToastContext.Provider>
  );
};
