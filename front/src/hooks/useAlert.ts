import { IToastContext } from "@/@types/Toast";
import { ToastContext } from "@/context/ToastContext";
import { useContext } from "react";

export const useAlert = () => {
  return useContext(ToastContext) as IToastContext;
};

export default useAlert;
