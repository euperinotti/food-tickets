import { HTMLAttributes } from "react";

export interface HomeCardProps extends HTMLAttributes<HTMLDivElement> {
  title: string;
  description: string | number;
}
