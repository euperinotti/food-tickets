"use client";
import { ToastStatus } from "@/@types/Toast";
import { toast, ToastOptions } from "react-toastify";

export const useAlert = () => {
  const toastConfig: ToastOptions = {
    position: "bottom-right",
    autoClose: 3500,
    closeOnClick: true,
    draggable: false,
    containerId: 1,
  };

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

  return {
    notify,
  };
};

export default useAlert;
