import { useDataTable } from "@/hooks/useDataTable";
import usePagination from "@/hooks/usePagination";
import { DataTableReducer } from "@/reducer/DataTableReducer";
import { ChangeEvent, useEffect } from "react";
import { InputText } from "../Input/InputText";
import { TableHeadCell } from "./HeadCell";
import { TableRow } from "./Row";
import { TableProps } from "./types";

export const Table = <T extends object>({ data, columns }: TableProps<T>) => {
  const { config, setConfig, search, sort } = useDataTable<T>(data, columns);

  const { currentRows, currentPage, totalPages, paginate } = usePagination(
    config.sortedData,
    config.rowsPerPage
  );

  const reducer = DataTableReducer<T>({
    config,
    currentPage,
    paginate,
    totalPages,
  })

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
    <div className="overflow-visible">
      <div className="mb-4" >
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
                    {column.key === "status"
                      ? reducer.renderStatusPill(row[column.key as any])
                      : row[column.key as any]}
                  </td>
                ))}
              </TableRow>
            ))}
          </tbody>
        </table>
      </div>

      {reducer.renderTableFooter()}
    </div>
  );
};