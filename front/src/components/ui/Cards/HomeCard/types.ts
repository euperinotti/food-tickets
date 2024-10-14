import { HTMLAttributes, ReactNode } from "react";

export interface HomeCardProps extends HTMLAttributes<HTMLDivElement> {
  title: string;
  description: string | number;
  icon: ReactNode
}
