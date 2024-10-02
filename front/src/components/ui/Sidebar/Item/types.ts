import { AnchorHTMLAttributes } from "react";
import { IconType } from "react-icons";

export interface SidebarItemProps
  extends AnchorHTMLAttributes<HTMLAnchorElement> {
  icon: IconType;
  label: string;
  href: string;
  isOpen: boolean;
}
