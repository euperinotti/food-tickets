import { EmployeeStatus } from "@/@types/IEmployee";
import { useDataTable } from "@/hooks/useDataTable";
import usePagination from "@/hooks/usePagination";
import { DataTableReducer } from "@/reducer/DataTableReducer";
import { ChangeEvent, useEffect, useRef, useState } from "react";
import { InputText } from "../Input/InputText";
import { DateFilter } from "./Filters/DateFilter";
import { TableFooter } from "./Footer";
import { TableHeadCell } from "./HeadCell";
import { TableRow } from "./Row";
import { TableColumns, TableProps } from "./types";

export const Table = <T extends object>({
  data,
  columns,
  onEditRow,
}: TableProps<T>) => {
  const tableRef = useRef(null)
  const [filteredData, setFilteredData] = useState<T[]>([]);
  const { config, setConfig, search, sort } = useDataTable<T>(
    filteredData,
    columns
  );
  const { currentRows, currentPage, totalPages, paginate } = usePagination(
    config.sortedData,
    config.rowsPerPage
  );

  const reducer = DataTableReducer<T>({
    config,
    currentPage,
    paginate,
    totalPages,
  });

  useEffect(() => {
    setConfig({ ...config, sortedData: filteredData });
  }, [filteredData]);

  useEffect(() => {
    setFilteredData([...data]);
  }, [data])

  const handleSearch = (event: ChangeEvent<HTMLInputElement>) => {
    search(event.target.value);
  };

  const handleSort = (column: string) => {
    sort(column as keyof T);
  };

  const handleFilter = (startDate: Date | null, endDate: Date | null) => {
    const filtered = data.filter((item: any) => {
      const createdAt = new Date(item.createdAt);
      const updatedAt = new Date(item.updatedAt);

      const isCreatedInRange =
        startDate && endDate
          ? createdAt >= startDate && createdAt <= endDate
          : true;
      const isUpdatedInRange =
        startDate && endDate
          ? updatedAt >= startDate && updatedAt <= endDate
          : true;

      return isCreatedInRange || isUpdatedInRange;
    });

    setFilteredData(filtered);
  };

  return (
    <div className="overflow-visible">
      <div className="flex gap-4 mb-4">
        <InputText
          placeholder="Pesquisar..."
          value={config.searchQuery}
          onChange={handleSearch}
        />
        <DateFilter onFilter={handleFilter} />
      </div>

      <div className="overflow-x-auto relative sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400" ref={tableRef}>
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
              <th></th>
            </tr>
          </thead>
          <tbody>
            {data.length > 0 &&
              currentRows.map((row: T, rowIndex: number) => (
                <TableRow key={rowIndex}>
                  {columns.map((column: TableColumns<T>) => (
                    <td key={column.key as string} className="px-6 py-4">
                      {column.key === "status"
                        ? reducer.renderStatusPill(
                            row[column.key as keyof T] as EmployeeStatus
                          )
                        : (row[column.key as keyof T] as string)}
                    </td>
                  ))}
                  <td className="px-6 py-4">
                    <button
                      className="text-blue-600 hover:underline"
                      onClick={() => onEditRow(row)}
                    >
                      Editar
                    </button>
                  </td>
                </TableRow>
              ))}
            {data.length === 0 && (
              <TableRow>
                <td colSpan={columns.length + 1} className="px-6 py-4">
                  Nenhum resultado encontrado
                </td>
              </TableRow>
            )}
          </tbody>
        </table>
      </div>
      <TableFooter
        config={config}
        currentPage={currentPage}
        paginate={paginate}
        totalPages={totalPages}
        ref={tableRef}
      />
    </div>
  );
};
