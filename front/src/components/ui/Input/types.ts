import { InputHTMLAttributes } from "react";

export interface GenericInputProps
  extends InputHTMLAttributes<HTMLInputElement> {
  label?: string;
}
