import { useDataTable } from "@/hooks/useDataTable";
import usePagination from "@/hooks/usePagination";
import { DataTableReducer } from "@/reducer/DataTableReducer";
import { Dialog } from "@headlessui/react";
import { ChangeEvent, useEffect, useState } from "react";
import { NewEntryForm } from "../Dialog/Forms/NewEmployee";
import { InputText } from "../Input/InputText";
import { DateFilter } from "./Filters/DateFilter";
import { TableHeadCell } from "./HeadCell";
import { TableRow } from "./Row";
import { TableProps } from "./types";

export const Table = <T extends object>({ data, columns }: TableProps<T>) => {
  const [filteredData, setFilteredData] = useState<T[]>(data);
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

  const handleSearch = (event: ChangeEvent<HTMLInputElement>) => {
    search(event.target.value);
  };

  const handleSort = (column: string) => {
    sort(column as keyof T);
  };

  const openModal = (row: T) => {
    setConfig({ ...config, isModalOpen: true, selectedRow: row });
  };

  const closeModal = () => {
    setConfig({ ...config, isModalOpen: false, selectedRow: null });
  };

  const handleFilter = (startDate: Date | null, endDate: Date | null) => {
    const filtered = data.filter((item: T) => {
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
      {config.isModalOpen && (
        <Dialog
          className="fixed z-50"
          open={config.isModalOpen}
          onClose={() => closeModal()}
        >
          <NewEntryForm entry={config.selectedRow} />
        </Dialog>
      )}
      <div className="flex gap-4 mb-4">
        <InputText
          placeholder="Pesquisar..."
          value={config.searchQuery}
          onChange={handleSearch}
        />
        <DateFilter onFilter={handleFilter} />
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
                <td className="px-6 py-4">
                  <button
                    className="text-blue-600 hover:underline"
                    onClick={() => openModal(row)}
                  >
                    Editar
                  </button>
                </td>
              </TableRow>
            ))}
          </tbody>
        </table>
      </div>

      {reducer.renderTableFooter()}
    </div>
  );
};
