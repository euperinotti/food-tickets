export interface TableColumns<T> {
  title: string;
  key: keyof T;
}

export interface TableProps<T> {
  data: T[];
  columns: TableColumns<T>[];
}

export interface TableConfig<T> {
  searchQuery: string;
  sortColumn: keyof T | null;
  sortOrder: "asc" | "desc";
  sortedData: T[];
  rowsPerPage: number;
}
