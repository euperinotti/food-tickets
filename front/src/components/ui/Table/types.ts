export interface TableColumns<T> {
  title: string;
  key: string;
}

export interface TableProps<T> {
  data: T[];
  columns: TableColumns<T>[];
  onEditRow: (row: T) => void;
}

export interface TableConfig<T> {
  searchQuery: string;
  sortColumn: keyof T | null;
  sortOrder: "asc" | "desc";
  sortedData: T[];
  rowsPerPage: number;
  isModalOpen: boolean;
  selectedRow: T | null;
}
