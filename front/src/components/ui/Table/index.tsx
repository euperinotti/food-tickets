import { useDataTable } from "@/hooks/useDataTable";
import usePagination from "@/hooks/usePagination";
import { ChangeEvent, useEffect } from "react";
import { LuChevronLeft, LuChevronRight } from "react-icons/lu";
import { InputText } from "../Input/InputText";
import { TableFooterButton } from "./Footer/Button";
import { TableHeadCell } from "./HeadCell";
import { TableRow } from "./Row";
import { TableProps } from "./types";

export const Table = <T extends object>({ data, columns }: TableProps<T>) => {
  const { config, setConfig, search, sort } = useDataTable<T>(data, columns);

  const { currentRows, currentPage, totalPages, paginate } = usePagination(
    config.sortedData,
    config.rowsPerPage
  );

  useEffect(() => {
    setConfig({ ...config, sortedData: data });
  }, [data]);

  const handleSearch = (event: ChangeEvent<HTMLInputElement>) => {
    search(event.target.value);
  };

  const handleSort = (column: string) => {
    sort(column as keyof T);
  };

  return (
    <div>
      <div className="mb-4">
        <InputText
          placeholder="Pesquisar..."
          value={config.searchQuery}
          onChange={handleSearch}
        />
      </div>

      <div className="overflow-x-auto relative sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              {columns.map((column) => (
                <TableHeadCell
                  key={column.key as string}
                  scope="col"
                  onClick={() => handleSort(column.key as string)}
                  title={column.title as string}
                  isSorted={config.sortColumn === column.key}
                  sortOrder={config.sortOrder}
                />
              ))}
            </tr>
          </thead>
          <tbody>
            {currentRows.map((row: T, rowIndex: number) => (
              <TableRow key={rowIndex}>
                {columns.map((column) => (
                  <td key={column.key as string} className="px-6 py-4">
                    {row[column.key] as string}
                  </td>
                ))}
              </TableRow>
            ))}
          </tbody>
        </table>
      </div>

      <div className="flex justify-between items-center mt-4">
        <span className="text-sm text-gray-700 dark:text-gray-400">
          Showing {config.rowsPerPage * (currentPage - 1) + 1} to{" "}
          {Math.min(config.rowsPerPage * currentPage, config.sortedData.length)}{" "}
          of {config.sortedData.length} entries
        </span>
        <div className="inline-flex mt-2 xs:mt-0">
          <TableFooterButton
            content={<LuChevronLeft className="text-slate-800" />}
            onClick={() => paginate(currentPage - 1)}
            disabled={currentPage === 1}
            className={`${
              currentPage === totalPages && "opacity-50 cursor-not-allowed"
            }`}
          />
          {[...Array(totalPages).keys()].map((num) => (
            <button
              key={num + 1}
              onClick={() => paginate(num + 1)}
              className={`px-4 py-2 text-sm font-medium ${
                currentPage === num + 1
                  ? "bg-blue-600 text-white"
                  : "bg-white text-blue-600 border border-gray-300 hover:bg-gray-100"
              }`}
            >
              {num + 1}
            </button>
          ))}
          <TableFooterButton
            content={<LuChevronRight className="text-slate-800" />}
            onClick={() => paginate(currentPage + 1)}
            disabled={currentPage === totalPages}
            className={`${
              currentPage === totalPages && "opacity-50 cursor-not-allowed"
            }`}
          />
        </div>
      </div>
    </div>
  );
};
