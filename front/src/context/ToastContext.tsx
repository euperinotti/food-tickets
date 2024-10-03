import { IToastContext, ToastStatus } from "@/@types/Toast";
import { createContext, ReactNode } from "react";
import { toast, ToastContainer, ToastOptions } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

interface ToastProviderProps {
  children: ReactNode;
}

export const ToastContext = createContext<IToastContext | undefined>(undefined);

const toastConfig: ToastOptions = {
  position: "bottom-right",
  autoClose: 3500,
  closeOnClick: true,
  draggable: false,
};

export const ToastProvider = ({ children }: ToastProviderProps) => {
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
    <ToastContext.Provider value={{ notify }}>
      {children}
      <ToastContainer />
    </ToastContext.Provider>
  );
};