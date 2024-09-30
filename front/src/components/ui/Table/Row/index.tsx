import { HTMLAttributes } from "react";

export interface TableRowProps extends HTMLAttributes<HTMLTableRowElement>{

}

export const TableRow = ({ children }: TableRowProps) => {
  return <tr className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">{children}</tr>;
};
