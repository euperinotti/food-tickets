import { ButtonHTMLAttributes } from "react";
import { IconType } from "react-icons";
import { ButtonType } from ".";

export interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  label: string;
  icon?: IconType;
  styleType: keyof typeof ButtonType;
}
