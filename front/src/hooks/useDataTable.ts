import { TableColumns, TableConfig } from "@/components/ui/Table/types";
import { useState } from "react";

export const useDataTable = <T>(data: T[], columns: TableColumns<T>[]) => {
  const [config, setConfig] = useState<TableConfig<T>>({
    searchQuery: "",
    sortColumn: null,
    sortOrder: "asc",
    sortedData: data,
    rowsPerPage: 6,
    isModalOpen: false,
    selectedRow: null,
  });

  const search = (query: string) => {
    const filteredData = query
      ? data.filter((row: any) =>
          columns.some((column: any) =>
            row[column.key as any]
              .toString()
              .toLowerCase()
              .includes(query.toLowerCase())
          )
        )
      : data;

    setConfig({ ...config, searchQuery: query, sortedData: filteredData });
  };

  const sort = (column: keyof T) => {
    const isAsc = config.sortColumn === column && config.sortOrder === "asc";
    const order = isAsc ? "desc" : "asc";

    const sorted = [...config.sortedData].sort((a: T, b: T) => {
      if (a[column as keyof T] < b[column as keyof T]) return isAsc ? -1 : 1;
      if (a[column as keyof T] > b[column as keyof T]) return isAsc ? 1 : -1;
      return 0;
    });

    setConfig({
      ...config,
      sortColumn: column,
      sortOrder: order,
      sortedData: sorted,
    });
  };

  return {
    config,
    setConfig,
    search,
    sort,
  };
};
