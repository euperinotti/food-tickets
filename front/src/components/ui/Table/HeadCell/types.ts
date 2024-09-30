import { HTMLAttributes } from "react";

export interface TableHeadCellProps
  extends HTMLAttributes<HTMLTableCellElement> {
  title: string;
  sortOrder: string;
  isSorted: boolean;
}
