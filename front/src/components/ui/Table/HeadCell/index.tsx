import { LuArrowDownWideNarrow, LuArrowUpWideNarrow } from "react-icons/lu";
import { TableHeadCellProps } from "./types";

export const TableHeadCell = ({
  title,
  isSorted,
  sortOrder,
  ...rest
}: TableHeadCellProps) => {
  return (
    <th {...rest} className={`px-6 py-3 cursor-pointer w-fit ${isSorted && "text-blue-500 bg-blue-100"}`}>
      <div className="flex items-center">
        {title}
        {isSorted && (
          <span className="ml-2">
            {sortOrder === "asc" ? (
              <LuArrowUpWideNarrow size="18px" />
            ) : (
              <LuArrowDownWideNarrow size="18px" />
            )}
          </span>
        )}
      </div>
    </th>
  );
};
