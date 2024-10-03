"use client";
import { ToastStatus } from "@/@types/Toast";
import { toast } from "react-toastify";

export const useAlert = () => {
  const notify = (status: ToastStatus, message: string) => {
    switch (status) {
      case ToastStatus.SUCCESS:
        toast.success(message);
        break;
      case ToastStatus.ERROR:
        toast.error(message);
        break;
      default:
        toast.info(message);
        break;
    }
  };

  return {
    notify,
  };
};

export default useAlert;
